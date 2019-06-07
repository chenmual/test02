package com.test.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RendererDemo {
	private final ExecutorService service;
	RendererDemo(ExecutorService exe){
		this.service = exe;
	}

	void renderPage(String source){
		List<String> info = new ArrayList<>();
		CompletionService<ReturnData> complete = new ExecutorCompletionService<ReturnData>(service);

		complete.submit(new Callable<ReturnData>() {
			@Override
			public ReturnData call() throws Exception {
				return new ReturnData("return");
			}
		});

		renderText(source);

		try {
			for(int i = 0, len = info.size(); i < len; i++){
				Future<ReturnData> f = complete.take();
				ReturnData ret = f.get();
				renderImage(ret);
			}
		} catch(ExecutionException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	void renderText(String text){
		//TODO
	}

	void renderImage(ReturnData img){
		System.out.println(img.name);
	}
}
class ReturnData{
	String name = "init";
	public ReturnData(String name){
		this.name = name;
	}
}
