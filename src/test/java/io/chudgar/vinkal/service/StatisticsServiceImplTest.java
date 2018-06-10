/**
 * 
 */
package io.chudgar.vinkal.service;

import java.time.Instant;

import org.junit.Assert;
import org.junit.Test;

import io.chudgar.vinkal.domain.Statistics;
import io.chudgar.vinkal.domain.Transaction;


/**
 * @author Vinkal
 *
 */
public class StatisticsServiceImplTest {

	/**
	 * Test method for {@link io.chudgar.vinkal.service.StatisticsServiceImpl#processTx(io.chudgar.vinkal.domain.Transaction)}.
	 */
	@Test
	public void testIfAllTransactionsAreWithin60SecondsTimeIntervald() {
		
		StatisticsServiceImpl impl = new StatisticsServiceImpl();
		for(int i=0; i < 5; i++){
			impl.processTx(new Transaction(1 + i, Instant.now().toEpochMilli()));
		}
		Statistics txStatistics = impl.getStatistics();
		
		Assert.assertEquals(5, txStatistics.getCount());		
		Assert.assertEquals(15.0, txStatistics.getSum(), 0.001);
		Assert.assertEquals(5.0, txStatistics.getMax(), 0.001);
		Assert.assertEquals(1.0, txStatistics.getMin(), 0.001);
		Assert.assertEquals(3.0, txStatistics.getAvg(), 0.001);
		
	}
	
	@Test
	public void testWhenNotAllTransactionsAreWithin60SecondsTimeIntervald() {
		
		StatisticsServiceImpl impl = new StatisticsServiceImpl();
		impl.processTx(new Transaction(1.0, 1528657343371L));
		impl.processTx(new Transaction(2.0, 1528657531373L));
		
		for(int i=2; i < 5; i++){
			impl.processTx(new Transaction(1 + i, Instant.now().toEpochMilli()));
		}
		Statistics txStatistics = impl.getStatistics();
		
		Assert.assertEquals(3, txStatistics.getCount());		
		Assert.assertEquals(12.0, txStatistics.getSum(), 0.001);
		Assert.assertEquals(5.0, txStatistics.getMax(), 0.001);
		Assert.assertEquals(3.0, txStatistics.getMin(), 0.001);
		Assert.assertEquals(4.0, txStatistics.getAvg(), 0.001);
		
	}

	
}
