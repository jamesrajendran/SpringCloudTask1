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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableTask
public class UnzipFiles {

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new UnzipFilesCommandLineRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(UnzipFiles.class, args);
	}

	public static class UnzipFilesCommandLineRunner implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			System.out.println("Hello World!");
			String filePath = "C:\\work\\OpenIngest\\testData";
			String fileZip = "unzipTest.zip";
			byte[] buffer = new byte[1024];
			ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath+"\\"+fileZip));
			ZipEntry zipEntry = zis.getNextEntry();
			while(zipEntry != null){
				String fileName = zipEntry.getName();
				File newFile = new File(filePath+"\\"+"unzipTest\\" + fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
			fos.close();
			zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			
		}

	}
}
