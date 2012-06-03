package tool.automator.executor.standalone.thread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import tool.automator.executor.datamanager.ElementDataManager;
import tool.automator.executor.datamanager.ElementValueDataManager;
import tool.automator.executor.datamanager.PageDataManager;
import tool.automator.executor.datamanager.ProjectDataManager;

public class PooledExecutorService {
	protected int _poolSize = 0;
	protected ExecutorService _executors;
	private Set<Future<TestScriptExecutor>> _futures;
	private int _submittedTasks = 0;

	private static final int WAIT_TIME = 60000;
	private static final int MAX_TASKS = 1000;
	private static final int MIN_TASKS = 100;

	public PooledExecutorService(int threadCount) {
		this._futures = Collections.synchronizedSet(new HashSet<Future<TestScriptExecutor>>());
		this._poolSize = threadCount;
		this._executors = Executors.newFixedThreadPool(this._poolSize);
	}

	@SuppressWarnings("unchecked")
	public void start(ProjectDataManager projectDAO, PageDataManager pageDAO, ElementDataManager elementDAO,
			ElementValueDataManager elementValueDAO, String testScriptFileName) {
		try {
			if (_futures.size() > MAX_TASKS) {
				System.out.println("WAITING FOR TASKS TO BE HANDLED (" + _submittedTasks + " SUBMITTED).");
				waitForEnd(MIN_TASKS);
				System.out.println("ADDING NEW TASKS.");
			}

			Future<TestScriptExecutor> f = (Future<TestScriptExecutor>) _executors.submit(new TestScriptExecutor(
					projectDAO, pageDAO, elementDAO, elementValueDAO, testScriptFileName));

			synchronized (_futures) {
				_futures.add(f);
			}

			_submittedTasks++;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForEnd() {
		try {
			waitForEnd(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void waitForEnd(int end) {
		while (_futures.size() > end) {
			try {
				Boolean wait = new Boolean(true);
				synchronized (wait) {
					wait.wait(WAIT_TIME);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Remaining files: " + _futures.size());

			Iterator<Future<TestScriptExecutor>> i = _futures.iterator();
			synchronized (i) {
				while (i.hasNext()) {
					Future<TestScriptExecutor> f = (Future<TestScriptExecutor>) i.next();
					if (f.isDone())
						i.remove();
				}
			}
		}

		if (end == 0) {
			System.out.println("Shutting down.");
			_executors.shutdown();
		}
	}
}
