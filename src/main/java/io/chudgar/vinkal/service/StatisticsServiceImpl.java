/**
 * 
 */
package io.chudgar.vinkal.service;

import java.util.concurrent.atomic.AtomicReferenceArray;

import org.springframework.stereotype.Service;

import io.chudgar.vinkal.common.utils.DateTimeUtil;
import io.chudgar.vinkal.domain.Statistics;
import io.chudgar.vinkal.domain.Transaction;
import io.chudgar.vinkal.domain.TxStatistics;

/**
 * @author Vinkal
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	private AtomicReferenceArray<TxStatistics> txStatisticsArray; // Immutable
	private final static int TX_TIME = 60; // can be configurable. Move to
											// config file

	private TxStatistics[] txs;

	public StatisticsServiceImpl() {
		this.txs = new TxStatistics[TX_TIME]; // contains Immutable TxStatistics
		this.txStatisticsArray = new AtomicReferenceArray<>(txs); // Thread safe
	}

	// Time complexity - O(1) & Space Complexity - O(1) as txs (array) size is fixed i.e. 60
	// Thread safe as TxStatistics is immutable. Also, AtomicReferenceArray is used.

	@Override
	public void processTx(Transaction tx) {

		int idx = (int) DateTimeUtil.getSecond(tx.getTimestamp()) % TX_TIME;

		if (txStatisticsArray.get(idx) == null) {
			txStatisticsArray.set(idx, new TxStatistics(tx.getAmount(), tx.getTimestamp()));
		} else {
			TxStatistics txStatistic = txStatisticsArray.get(idx);
			if (DateTimeUtil.isWithinInterval(tx.getTimestamp())) {
				txStatisticsArray.set(idx,
						new TxStatistics(txStatistic.getCount() + 1, txStatistic.getSum() + tx.getAmount(),
								Math.max(txStatistic.getMax(), tx.getAmount()),
								Math.min(txStatistic.getMin(), tx.getAmount()), tx.getTimestamp()));
			} else {
				txStatisticsArray.set(idx, new TxStatistics(tx.getAmount(), tx.getTimestamp()));
			}
		}
	}

	// Time Complexity: O(1)
	// Space Complexity: O(1)
	@Override
	public Statistics getStatistics() {

		TxStatistics aggregatedTxStatistics = new TxStatistics();

		for (int idx = 0; idx < txStatisticsArray.length(); idx++) {

			TxStatistics txStatistics = txStatisticsArray.get(idx);

			if (txStatistics != null && DateTimeUtil.isWithinInterval(txStatistics.getTimeStamp())) {
				aggregatedTxStatistics = new TxStatistics(aggregatedTxStatistics.getCount() + txStatistics.getCount(),
						aggregatedTxStatistics.getSum() + txStatistics.getSum(),
						Math.max(aggregatedTxStatistics.getMax(), txStatistics.getMax()),
						Math.min(aggregatedTxStatistics.getMin(), txStatistics.getMin()), 0);

			}
		}
		return aggregatedTxStatistics;
	}

}
