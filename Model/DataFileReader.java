package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataFileReader {
	private ArrayList<String> dataList;

	public  DataFileReader(String fileName) {
		BufferedReader reader = null;
		String tmp = null;
		ArrayList<String> tmpList = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while ((tmp = reader.readLine()) != null) {
				tmpList.add(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setDataList(tmpList);
	}

	public ArrayList<String> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<String> dataList) {
		this.dataList = dataList;
	}
}
