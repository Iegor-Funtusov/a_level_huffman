package ua.com.alevel.binary.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.binary.compress.Huffman;
import ua.com.alevel.binary.compress.Tree;
import ua.com.alevel.binary.data.MakeCode;
import ua.com.alevel.binary.data.ResultData;
import ua.com.alevel.binary.data.SortMakeCodeByWeight;
import ua.com.alevel.binary.util.BinaryUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class HuffmanServiceImpl implements HuffmanService {

    @Override
    public ResultData run(String text) {
        if (text != null && text.length() != 0) {
            ResultData resultData = new ResultData();
            resultData.setText(text);
            resultData.setBinaryText(BinaryUtil.convertToBinary(text));
            int[] data = new int[text.length()];
            for (int i = 0; i < text.length(); i++) {
                data[i] = text.charAt(i);
            }
            int max = Arrays.stream(data).summaryStatistics().getMax();
            Huffman h = new Huffman(max + 1);
            h.growTree(data);
            h.makeCode();
            text = h.coder(data);
            resultData.setBinaryCompressText(text);
            resultData.setResultText(h.decoder(text));
            List<MakeCode> makeCodes = Tree.getMakeCodes();
            Collections.sort(makeCodes, new SortMakeCodeByWeight());
            resultData.setMakeCodes(makeCodes);
            return resultData;
        } else {
            return null;
        }
    }
}
