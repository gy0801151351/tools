import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class  文件编码转换器 {

	public static void main(String[] args) throws Exception {
		String sourceEncode = "GBK";
		String targetEncode = "UTF-8";
		
		File sourceFolder = new File("E:\\SVN\\web\\trunk\\src");
		File targetFolder = new File("E:\\SVN\\web\\branches\\src");
		String[] projects = {
				"newService",
				"weixin"
		};
		
		if (!targetFolder.exists()) {
			targetFolder.mkdirs();
		}
		
		String targetFolderPath = targetFolder.getAbsolutePath();
		for (String project : projects) {
			Iterator<File> iterateFiles = FileUtils.iterateFiles(new File(sourceFolder.getAbsolutePath() + File.separator + project), new IOFileFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return !name.toLowerCase().endsWith(".svn");
				}
				
				@Override
				public boolean accept(File pathname) {
					return !pathname.getName().toLowerCase().endsWith(".svn");
				}
			}, new IOFileFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return !name.toLowerCase().endsWith(".svn");
				}
				
				@Override
				public boolean accept(File pathname) {
					return !pathname.getName().toLowerCase().endsWith(".svn");
				}
			});
			
			while (iterateFiles.hasNext()) {
				File file = iterateFiles.next();
				File targetFile = new File(targetFolderPath + File.separator + getRelativePath(file, sourceFolder));
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				if (isTextFile(file)) {
					copyTextFile(file, targetFile, sourceEncode, targetEncode);
					System.out.println(getRelativePath(file, sourceFolder));
				} else {
					FileUtils.copyFile(file, targetFile);
					System.err.println(getRelativePath(file, sourceFolder));
				}
			}
		}
		
	}
	
	public static String getRelativePath(File file, File baseFolder) {
		String basePath = baseFolder.getAbsolutePath();
		String filePath = file.getAbsolutePath();
		int index = filePath.indexOf(basePath);
		if (index < 0) {
			throw new UnsupportedOperationException("�ļ�����Ŀ¼ "+basePath+" ��: " + filePath);
		}
		return filePath.substring(index + basePath.length());
	}
	
	public static void copyTextFile(File sourceFile, File targetFile, String sourceEncode, String targetEncode) {
		int bufSize = 8192;
		try (FileReader fr = new FileReader(sourceFile);
			 BufferedReader br = new BufferedReader(fr, bufSize);
			 FileWriter fw = new FileWriter(targetFile);
			 BufferedWriter bw = new BufferedWriter(fw, bufSize)
			 ) {
			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(new String(line.getBytes(sourceEncode), targetEncode));
				bw.write("\r\n");
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isTextFile(File file) {
		String[] textExt = {
				".java",
				".xml",
				".css",
				".js",
				".jsp",
				".txt",
				".htm",
				".html",
				".xhtml",
				".tld",
				
		};
		String lowerFileName = file.getName();
		for (String ext : textExt) {
			if (lowerFileName.endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
}
