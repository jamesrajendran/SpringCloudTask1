package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.cloud.task.repository.support.SimpleTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableTask
public class DemoApplication {

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new HelloWorldCommandLineRunner();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	public static class HelloWorldCommandLineRunner implements CommandLineRunner
	{
		//SimpleTaskRepository c;
		@Override
		public void run(String... strings) throws Exception {
			System.out.println("Hello World!");
		}

		@AfterTask
		public void onTaskEnd(TaskExecution taskExecution) {
			System.out.println("Job successfully finished!");
			
		}

		@FailedTask
		public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
			System.out.println("Job failed!");
			
		}

		@BeforeTask
		public void onTaskStartup(TaskExecution taskExecution) {
			System.out.println("Job is being started!");
			
		}
		
	}
}
