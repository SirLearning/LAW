package pgl.LAW.V4DB.vmap4;

import javax.swing.*;
import java.awt.*;

public class GetGermplasmInfo extends JPanel {
    JTextField GID;
    JTextField GRepeat;
    JTextField bam;
    JTextField accession;
    JTextField ChineseName;
    JTextField taxa;
    JTextField dataSet;
    public GetGermplasmInfo(){
        JLabel smpGID = new JLabel("sample GID");
        GID = new JTextField(10);
        JLabel smpGRepeat = new JLabel("sample G repeat");
        GRepeat = new JTextField(10);
        JLabel smpBam = new JLabel("sample bam file name");
        bam = new JTextField(10);
        JLabel smpAccession = new JLabel("sample accession");
        accession = new JTextField(10);
        JLabel smpChineseName = new JLabel("sample Chinese name");
        ChineseName = new JTextField(10);
        JLabel smpTaxa = new JLabel("sample taxonomy");
        taxa = new JTextField(10);
        JLabel smpDataSet = new JLabel("sample dataset");
        dataSet = new JTextField(10);
        this.setLayout(new FlowLayout());
        this.add(smpGID);
        this.add(GID);
        this.add(smpGRepeat);
        this.add(GRepeat);
        this.add(smpBam);
        this.add(bam);
        this.add(smpAccession);
        this.add(accession);
        this.add(smpChineseName);
        this.add(ChineseName);
        this.add(smpTaxa);
        this.add(taxa);
        this.add(smpDataSet);
        this.add(dataSet);
    }
    public String getGID(){
        return GID.getText();
    }
    public String getGRepeat(){
        return GRepeat.getText();
    }
    public String getBam(){
        return bam.getText();
    }
    public String getAccession(){
        return accession.getText();
    }
    public String getChineseName(){
        return ChineseName.getText();
    }
    public String getTaxa(){
        return taxa.getText();
    }
    public String getDataSet(){
        return dataSet.getText();
    }
}
