package hadoop.infra.test4;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;
import java.util.logging.Logger;

public class WebApp {

	private static final Logger logger = Logger.getLogger("io.saagie.example.hdfs.Main");

	public static void main(String[] args) throws Exception {
		String path = "/user/team0/test/";
		String fileName = "hello.csv";
		String fileContent = "hello;world";
		Configuration conf = new Configuration();
		conf.addResource(new Path("conf/core-site.xml"));
		conf.addResource(new Path("conf/hdfs-site.xml"));
		conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
		conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
		System.setProperty("HADOOP_USER_NAME", "team0");
		System.setProperty("hadoop.home.dir", "/");
		FileSystem fs = FileSystem.get(conf);
		Path workingDir = fs.getWorkingDirectory();
		Path newFolderPath = new Path(path);
		if (!fs.exists(newFolderPath)) {
			fs.mkdirs(newFolderPath);
		}
		Path hdfswritepath = new Path(newFolderPath + "/" + fileName);
		FSDataOutputStream outputStream = fs.create(hdfswritepath);
		outputStream.writeBytes(fileContent);
		outputStream.close();
		Path hdfsreadpath = new Path(newFolderPath + "/" + fileName);
		FSDataInputStream inputStream = fs.open(hdfsreadpath);
		String out = IOUtils.toString(inputStream, "UTF-8");
		inputStream.close();
		fs.close();
	}
}