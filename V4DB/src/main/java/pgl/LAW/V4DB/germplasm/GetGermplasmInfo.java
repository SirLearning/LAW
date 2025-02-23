package pgl.LAW.V4DB.germplasm;

import javax.swing.*;
import java.awt.*;

public class GetGermplasmInfo extends JPanel {
    JTextField GID;
    JTextField GRepeat;
    JTextField Bams;
    JTextField Accession;
    JTextField Chinese_name;
    JTextField Full_Taxonomy;
    JTextField Data_sources;
    public GetGermplasmInfo(){
        JLabel smpGID = new JLabel("sample GID");
        GID = new JTextField(10);
        JLabel smpGRepeat = new JLabel("sample G repeat");
        GRepeat = new JTextField(10);
        JLabel smpBam = new JLabel("sample bam file name");
        Bams = new JTextField(10);
        JLabel smpAccession = new JLabel("sample accession");
        Accession = new JTextField(10);
        JLabel smpChineseName = new JLabel("sample Chinese name");
        Chinese_name = new JTextField(10);
        JLabel smpTaxa = new JLabel("sample taxonomy");
        Full_Taxonomy = new JTextField(10);
        JLabel smpDataSet = new JLabel("sample dataset");
        Data_sources = new JTextField(10);
        this.setLayout(new FlowLayout());
        this.add(smpGID);
        this.add(GID);
        this.add(smpGRepeat);
        this.add(GRepeat);
        this.add(smpBam);
        this.add(Bams);
        this.add(smpAccession);
        this.add(Accession);
        this.add(smpChineseName);
        this.add(Chinese_name);
        this.add(smpTaxa);
        this.add(Full_Taxonomy);
        this.add(smpDataSet);
        this.add(Data_sources);
    }
    public String getGID(){
        return GID.getText();
    }
    public String getGRepeat(){
        return GRepeat.getText();
    }
    public String getBam(){
        return Bams.getText();
    }
    public String getAccession(){
        return Accession.getText();
    }
    public String getChineseName(){
        return Chinese_name.getText();
    }
    public String getTaxa(){
        return Full_Taxonomy.getText();
    }
    public String getDataSet(){
        return Data_sources.getText();
    }
}
