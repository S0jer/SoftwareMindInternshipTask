package org.example.csvfilesreader;

import org.example.businesscard.BusinessCard;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader implements FileReader {

    public static final String UTF8_BOM = "\uFEFF";

    public List<BusinessCard> read(String filePath) {
        try {
            List<BusinessCard> businessCardList = new ArrayList<>();
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            boolean firstRow = true;
            Integer lineCnt = 0;

            readAndSaveFileToList(bufferedReader, businessCardList, firstRow, lineCnt);
            bufferedReader.close();
            return businessCardList;

        } catch (FileNotFoundException e) {
            throw new WrongPathException("Wrong path to file", e);
        } catch (IOException e) {
            throw new BufferException("Can not read or close buffer ", e);
        }

    }

    private void readAndSaveFileToList(BufferedReader bufferedReader, List<BusinessCard> businessCardList, boolean firstRow, Integer lineCnt) throws IOException {
        List<String> tmpArr;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (firstRow) {
                line = deleteUTF8BOM(line);
                firstRow = false;
            }
            tmpArr = List.of(line.split(","));
            checkTaskRequirements(tmpArr, lineCnt);
            businessCardList.add(new BusinessCard(tmpArr.get(0), tmpArr.get(1), tmpArr.get(2)));
            lineCnt += 1;
        }
    }

    private String deleteUTF8BOM(String line) {
        if (line.startsWith(UTF8_BOM)) {
            line = line.substring(1);
        }
        return line;
    }

    private void checkTaskRequirements(List<String> tmpArr, Integer lineCnt) {
        String exceptionMessage = "Wrong argument in line ";
        if (tmpArr.size() != 3) {
            throw new IllegalArgumentException(exceptionMessage + lineCnt + " and column " + (tmpArr.size() - 1));
        }
        for (int i = 0; i < tmpArr.size(); i++) {
            if (tmpArr.get(i).isEmpty()) {
                throw new IllegalArgumentException(exceptionMessage + lineCnt + " and column " + i);
            }
        }
    }


    public static class WrongPathException extends RuntimeException {
        public WrongPathException(String errMessage, Throwable err) {
            super(errMessage, err);
        }
    }

    public static class BufferException extends RuntimeException {
        public BufferException(String errMessage, Throwable err) {
            super(errMessage, err);
        }
    }
}
