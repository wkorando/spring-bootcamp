package com.ibm.developer.springbootcamp;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceGenerator {

	  private AtomicLong value = new AtomicLong(0);

	public long nextVal() {
		return value.incrementAndGet();
	}
}
