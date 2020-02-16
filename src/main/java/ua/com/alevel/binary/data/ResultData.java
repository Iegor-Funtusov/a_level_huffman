package ua.com.alevel.binary.data;

import lombok.Data;

import java.util.List;

@Data
public class ResultData {

    private String text;
    private String resultText;
    private String binaryText;
    private String binaryCompressText;
    private List<MakeCode> makeCodes;
}
