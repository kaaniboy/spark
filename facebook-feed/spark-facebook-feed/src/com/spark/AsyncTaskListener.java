package com.spark;

public interface AsyncTaskListener<E> {
	void onTaskComplete(E data);
}
