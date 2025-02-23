package pgl.LAW.V4DB.germplasm;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ietf.jgss.GSSName;

import java.io.*;
import java.sql.*;

public class ReadData {
    private static final String URL = "jdbc:mysql://localhost:3306/germplasm";
    private static final String USER = "root";
    private static final String PASSWORD = "hanalijin";

    public static void main(String[] args) {
//        readData("vmap4", "taxonomy", 0);
//        readData("vmap4", "provenance", 0);
//        readData("vmap4", "germplasm", 0);
//        readData("vmap4", "wgs", 0);
//        readData("as", "wega", 0);
//        readData("vmap4", "experiment", 0);
//        readData("vmap4", "inherit", 0);
        readData("vmap4", "outlib", 0);
//        readData("vmap3", "outlib", 1);
//        readData("vmap3", "outlib", 2);
//        readTable_AS("V4DB/src/main/resources/V4_germplasm/A&Sgenome.xlsx");
//        readTable_V4("V4DB/src/main/resources/V4_germplasm/V4_germplasm.xlsx");
//        readTable_add_bam("V4DB/src/main/resources/V4_germplasm/V4_germplasm.xlsx");
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // or handle the error as needed
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private static double getCellValueAsDouble(Cell cell) {
        if (cell == null) {
            return 0; // or handle the error as needed
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0; // or handle the error as needed
                }
            default:
                return 0; // or handle the error as needed
        }
    }

    public static void readData(String source, String table, int sheet_num) {
        Connection conn = null;
        PreparedStatement pS = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // vmap3 0:1 1:746 2:796
            // AS 0:800
            // vmap4 0:802
            // final 2223
            int pglNumber = 2223;

            if (source == "vmap3") {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                conn.setAutoCommit(false);
                FileInputStream file = new FileInputStream(new File("V4DB/src/main/resources/V4_germplasm/种质存储表.xlsx"));
                Workbook workbook = new XSSFWorkbook(file);
                Sheet sheet = workbook.getSheetAt(sheet_num);

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;
                    String Bams = getCellValueAsString(row.getCell(0));
                    String Accession = getCellValueAsString(row.getCell(1));
                    String ID_yin = getCellValueAsString(row.getCell(2));
                    String Sample_name = getCellValueAsString(row.getCell(3));
                    String English_name = getCellValueAsString(row.getCell(4));
                    String Chinese_name = getCellValueAsString(row.getCell(5));
                    String Data_sources = getCellValueAsString(row.getCell(6));
                    String Genome_type = getCellValueAsString(row.getCell(7));
                    int Ploidy = Genome_type.length();
                    String Type_original8 = getCellValueAsString(row.getCell(8));
                    String Type_USDA8 = getCellValueAsString(row.getCell(9));
                    String Common_name = getCellValueAsString(row.getCell(10));
                    String Type_final = getCellValueAsString(row.getCell(11));
                    double Latitude = getCellValueAsDouble(row.getCell(12));
                    double Longitude = getCellValueAsDouble(row.getCell(13));
                    String Continent = getCellValueAsString(row.getCell(14));
                    String Continent_Abbreviate = getCellValueAsString(row.getCell(15));
                    String Country = getCellValueAsString(row.getCell(16));
                    String Country_Abbreviate = getCellValueAsString(row.getCell(17));
                    String City_USDA = getCellValueAsString(row.getCell(18));
                    String Genus = getCellValueAsString(row.getCell(19));
                    String Species = getCellValueAsString(row.getCell(20));
                    String Full_Taxonomy = getCellValueAsString(row.getCell(21));
                    double Elevation = getCellValueAsDouble(row.getCell(22));
                    String Received_year_USDA = getCellValueAsString(row.getCell(23));
                    String Source_data_USDA = getCellValueAsString(row.getCell(24));
                    String Growth_habit = getCellValueAsString(row.getCell(25));
                    String Released_or_introduced_year = getCellValueAsString(row.getCell(26));
                    String Pedigree = getCellValueAsString(row.getCell(27));
                    String Eco_region = getCellValueAsString(row.getCell(28));
                    String Germplasm_bank = getCellValueAsString(row.getCell(29));
                    String Growth_class = getCellValueAsString(row.getCell(30));
                    String GID_update = getCellValueAsString(row.getCell(31));
                    String WEGA = getCellValueAsString(row.getCell(32));
                    String GID = Bams.split("\\.")[0].split("_")[0] + "0" + Bams.split("\\.")[0].split("_")[1];
                    int GRepeat = 0;
                    if (Bams.split("_")[0].equals("CS")) GID = Bams.split("\\.")[0];
                    if (table.equals("taxonomy")) {
                        // no repeat Taxonomy
                        String checkSql = "SELECT COUNT(*) FROM germplasm.taxonomy WHERE " +
                                "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                                "Common_name = ? AND Genome_type = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, Genus);
                        checkStmt.setString(2, Species);
                        checkStmt.setString(3, Full_Taxonomy);
                        checkStmt.setString(4, Common_name);
                        checkStmt.setString(5, Genome_type);
                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
//                        System.out.println(rs.getInt(1));
                        // insert data
                        if (rs.getInt(1) == 0) {
                            String sql = "insert into germplasm.taxonomy" +
                                    "(Genus, Species, Full_Taxonomy, Common_name, Genome_type, Ploidy)" +
                                    "values(?, ?, ?, ?, ?, ?)";
                            pS = conn.prepareStatement(sql);
                            pS.setString(1, Genus);
                            pS.setString(2, Species);
                            pS.setString(3, Full_Taxonomy);
                            pS.setString(4, Common_name);
                            pS.setString(5, Genome_type);
                            pS.setInt(6, Ploidy);
                            pS.executeUpdate();
                        }
                        rs.close();
                        checkStmt.close();
                    }
                    if (table.equals("provenance")) {
                        // check no repeat Provenance
                        String checkSql = "SELECT COUNT(*) FROM germplasm.provenance WHERE " +
                                "Continent = ? AND Continent_abbr = ? AND Country = ? AND " +
                                "Country_abbr = ? AND City = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, Continent);
                        checkStmt.setString(2, Continent_Abbreviate);
                        checkStmt.setString(3, Country);
                        checkStmt.setString(4, Country_Abbreviate);
                        checkStmt.setString(5, City_USDA);
                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
                        // insert data
                        if (rs.getInt(1) == 0) {
                            String sql = "insert into germplasm.provenance" +
                                    "(Continent, Continent_abbr, Country, Country_abbr, City)" +
                                    "value(?, ?, ?, ?, ?)";
                            pS = conn.prepareStatement(sql);
                            pS.setString(1, Continent);
                            pS.setString(2, Continent_Abbreviate);
                            pS.setString(3, Country);
                            pS.setString(4, Country_Abbreviate);
                            pS.setString(5, City_USDA);
                            pS.executeUpdate();
                        }
                        rs.close();
                        checkStmt.close();
                    }
                    if (table.equals("germplasm")) {
                        // get key numbers
                        int Taxonomy_number = 0;
                        int Provenance_number = 0;
                        String getSqlT = "select number from germplasm.taxonomy where " +
                                "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                                "Common_name = ? AND Genome_type = ?";
                        PreparedStatement getT = conn.prepareStatement(getSqlT);
                        getT.setString(1, Genus);
                        getT.setString(2, Species);
                        getT.setString(3, Full_Taxonomy);
                        getT.setString(4, Common_name);
                        getT.setString(5, Genome_type);
                        ResultSet rs1 = getT.executeQuery();
                        if (rs1.next()) {
                            Taxonomy_number = rs1.getInt(1);
                        }
//                        System.out.println(Taxonomy_number);
                        rs1.close();
                        String getSqlP = "select number from germplasm.provenance where " +
                                "Continent = ? AND Continent_abbr = ? AND Country = ? AND " +
                                "Country_abbr = ? AND City = ?";
                        PreparedStatement getP = conn.prepareStatement(getSqlP);
                        getP.setString(1, Continent);
                        getP.setString(2, Continent_Abbreviate);
                        getP.setString(3, Country);
                        getP.setString(4, Country_Abbreviate);
                        getP.setString(5, City_USDA);
                        ResultSet rs2 = getP.executeQuery();
                        if (rs2.next()) {
                            Provenance_number = rs2.getInt(1);
                        }
//                        System.out.println(Provenance_number);
                        rs2.close();
                        // check accession
                        String checkSql = "SELECT COUNT(*) FROM germplasm.germplasm WHERE Accession = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, Accession);
                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
                        // insert data
                        if (Accession.equals("-") || Accession.equals("NA") || Accession.isEmpty()) {
                            String getSql = "select count(*) from germplasm.germplasm where " +
                                    "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                                    "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                                    "Longitude = ? and Elevation = ? and Introduced_year = ? and Eco_region = ?";
                            PreparedStatement getStmt = conn.prepareStatement(getSql);
                            getStmt.setString(1, English_name);
                            getStmt.setString(2, Sample_name);
                            getStmt.setString(3, Chinese_name);
                            getStmt.setString(4, Type_final);
                            getStmt.setInt(5, Taxonomy_number);
                            getStmt.setString(6, Pedigree);
                            getStmt.setString(7, Growth_habit);
                            getStmt.setInt(8, Provenance_number);
                            getStmt.setDouble(9, Latitude);
                            getStmt.setDouble(10, Longitude);
                            getStmt.setDouble(11, Elevation);
                            getStmt.setString(12, Released_or_introduced_year);
                            getStmt.setString(13, Eco_region);
                            ResultSet rs3 = getStmt.executeQuery();
                            rs3.next();
                            if (rs3.getInt(1) == 0) {
                                String PGL_accession = "PGL" + String.format("%05d", pglNumber);
                                pglNumber++;
//                            System.out.println(pglNumber);
                                String sql = "insert into germplasm.germplasm " +
                                        "(Accession, Name, Other_name, Chinese_name, Wheat_type, " +
                                        "Taxonomy_number, Pedigree, Growth_habit, Provenance_number, Latitude, " +
                                        "Longitude, Elevation, Introduced_year, Eco_region) " +
                                        "value (?, ?, ?, ?, ?, " +
                                        "?, ?, ?, ?, ?, " +
                                        "?, ?, ?, ?)";
                                pS = conn.prepareStatement(sql);
                                pS.setString(1, PGL_accession);
                                pS.setString(2, English_name);
                                pS.setString(3, Sample_name);
                                pS.setString(4, Chinese_name);
                                pS.setString(5, Type_final);
                                pS.setInt(6, Taxonomy_number);
                                pS.setString(7, Pedigree);
                                pS.setString(8, Growth_habit);
                                pS.setInt(9, Provenance_number);
                                pS.setDouble(10, Latitude);
                                pS.setDouble(11, Longitude);
                                pS.setDouble(12, Elevation);
                                pS.setString(13, Released_or_introduced_year);
                                pS.setString(14, Eco_region);
                                pS.executeUpdate();
                            }
                        } else if (rs.getInt(1) == 0) {
                            String sql = "insert into germplasm.germplasm " +
                                    "(Accession, Name, Other_name, Chinese_name, Wheat_type, " +
                                    "Taxonomy_number, Pedigree, Growth_habit, Provenance_number, Latitude, " +
                                    "Longitude, Elevation, Introduced_year, Eco_region) " +
                                    "value (?, ?, ?, ?, ?, " +
                                    "?, ?, ?, ?, ?, " +
                                    "?, ?, ?, ?)";
                            pS = conn.prepareStatement(sql);
                            pS.setString(1, Accession);
                            pS.setString(2, English_name);
                            pS.setString(3, Sample_name);
                            pS.setString(4, Chinese_name);
                            pS.setString(5, Type_final);
                            pS.setInt(6, Taxonomy_number);
                            pS.setString(7, Pedigree);
                            pS.setString(8, Growth_habit);
                            pS.setInt(9, Provenance_number);
                            pS.setDouble(10, Latitude);
                            pS.setDouble(11, Longitude);
                            pS.setDouble(12, Elevation);
                            pS.setString(13, Released_or_introduced_year);
                            pS.setString(14, Eco_region);
                            pS.executeUpdate();
                        }
                    }
                    if (table.equals("wgs")) {
                        // check accession
                        String checkSql = "SELECT COUNT(*) FROM germplasm.wgs WHERE GID = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, GID);
                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
                        if (rs.getInt(1) == 0) {
                            String sql = "insert into germplasm.wgs" +
                                    "(GID, GID_update, Bams, Dataset)" +
                                    "values(?, ?, ?, ?)";
                            pS = conn.prepareStatement(sql);
                            pS.setString(1, GID);
                            pS.setString(2, GID_update);
                            pS.setString(3, Bams);
                            pS.setString(4, source);
                            pS.executeUpdate();
                        }
                    }
                    if (table.equals("wega")) {
                        // check accession
                        if (!WEGA.isEmpty() && !WEGA.equals("-")) {
                            String checkSql = "SELECT COUNT(*) FROM germplasm.wega WHERE ID = ?";
                            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                            checkStmt.setString(1, WEGA);
                            ResultSet rs = checkStmt.executeQuery();
                            rs.next();
                            if (rs.getInt(1) == 0) {
                                String sql = "insert into germplasm.wega (ID) values(?)";
                                pS = conn.prepareStatement(sql);
                                pS.setString(1, WEGA);
                                pS.executeUpdate();
                            }
                        }
                    }
                    if (table.equals("experiment")) {
                        // get key numbers
                        int Taxonomy_number = 0;
                        int Provenance_number = 0;
                        String getSqlT = "select number from germplasm.taxonomy where " +
                                "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                                "Common_name = ? AND Genome_type = ?";
                        PreparedStatement getT = conn.prepareStatement(getSqlT);
                        getT.setString(1, Genus);
                        getT.setString(2, Species);
                        getT.setString(3, Full_Taxonomy);
                        getT.setString(4, Common_name);
                        getT.setString(5, Genome_type);
                        ResultSet rs1 = getT.executeQuery();
                        if (rs1.next()) {
                            Taxonomy_number = rs1.getInt(1);
                        }
//                        System.out.println(Taxonomy_number);
                        rs1.close();
                        String getSqlP = "select number from germplasm.provenance where " +
                                "Continent = ? AND Continent_abbr = ? AND Country = ? AND " +
                                "Country_abbr = ? AND City = ?";
                        PreparedStatement getP = conn.prepareStatement(getSqlP);
                        getP.setString(1, Continent);
                        getP.setString(2, Continent_Abbreviate);
                        getP.setString(3, Country);
                        getP.setString(4, Country_Abbreviate);
                        getP.setString(5, City_USDA);
                        ResultSet rs2 = getP.executeQuery();
                        if (rs2.next()) {
                            Provenance_number = rs2.getInt(1);
                        }
//                        System.out.println(Provenance_number);
                        rs2.close();
                        // get Accession
                        String getSql = "select Accession from germplasm.germplasm where " +
                                "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                                "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                                "Longitude = ? and Elevation = ? and Introduced_year = ? and Eco_region = ?";
                        PreparedStatement getStmt = conn.prepareStatement(getSql);
                        getStmt.setString(1, English_name);
                        getStmt.setString(2, Sample_name);
                        getStmt.setString(3, Chinese_name);
                        getStmt.setString(4, Type_final);
                        getStmt.setInt(5, Taxonomy_number);
                        getStmt.setString(6, Pedigree);
                        getStmt.setString(7, Growth_habit);
                        getStmt.setInt(8, Provenance_number);
                        getStmt.setDouble(9, Latitude);
                        getStmt.setDouble(10, Longitude);
                        getStmt.setDouble(11, Elevation);
                        getStmt.setString(12, Released_or_introduced_year);
                        getStmt.setString(13, Eco_region);
                        ResultSet rs3 = getStmt.executeQuery();
                        if (rs3.next()) {
                            Accession = rs3.getString(1);
                        }
                        // identify GRepeat
                        String repeatSql = "SELECT COUNT(*) FROM germplasm.experiment WHERE Accession = ?";
                        PreparedStatement repeatStmt = conn.prepareStatement(repeatSql);
                        repeatStmt.setString(1, Accession);
                        ResultSet rs4 = repeatStmt.executeQuery();
                        rs4.next();
                        GRepeat = rs4.getInt(1) + 1;
                        // check GID
                        String checkSql = "SELECT COUNT(*) FROM germplasm.experiment WHERE GID = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, GID);
                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
                        // insert data
                        if (rs.getInt(1) == 0) {
                            String sql = "insert into germplasm.experiment " +
                                    "(Accession, GID, GRepeat, WEGA_ID, Data_sources) " +
                                    "value (?, ?, ?, ?, ?)";
                            pS = conn.prepareStatement(sql);
                            pS.setString(1, Accession);
                            pS.setString(2, GID);
                            pS.setInt(3, GRepeat);
                            if (WEGA.equals("-") || WEGA.isEmpty()) {
                                pS.setNull(4, java.sql.Types.VARCHAR);
                            } else {
                                pS.setString(4, WEGA);
                            }
                            pS.setString(5, Data_sources);
                            pS.executeUpdate();
                        }
                    }
                    if (table.equals("inherit")) {
                        boolean USDA = !City_USDA.isEmpty()&&!City_USDA.equals("-");
                        boolean IGDB = !Type_original8.isEmpty()&&!Type_original8.equals("-");
                        boolean WGD_V7 = !ID_yin.isEmpty()&&!ID_yin.equals("-");
                        boolean CGRIS = Germplasm_bank.equals("CGRIS");
                        boolean USA = Germplasm_bank.equals("USA");
                        if (Germplasm_bank.equals("USDA")) USDA = true;
                        boolean CHN = Germplasm_bank.equals("CHN");
                        boolean NSGC = Germplasm_bank.equals("NationalSmallGrainsGermplasmResearchFacility,USDANAARSNAUnitedStates");
                        boolean CAAS = Germplasm_bank.equals("CAAS");
                        boolean HENU = Germplasm_bank.equals("HENU");
                        boolean UCDAVIS = Germplasm_bank.equals("UCDAVIS");
                        // get key numbers
                        int Taxonomy_number = 0;
                        int Provenance_number = 0;
                        String getSqlT = "select number from germplasm.taxonomy where " +
                                "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                                "Common_name = ? AND Genome_type = ?";
                        PreparedStatement getT = conn.prepareStatement(getSqlT);
                        getT.setString(1, Genus);
                        getT.setString(2, Species);
                        getT.setString(3, Full_Taxonomy);
                        getT.setString(4, Common_name);
                        getT.setString(5, Genome_type);
                        ResultSet rs1 = getT.executeQuery();
                        if (rs1.next()) {
                            Taxonomy_number = rs1.getInt(1);
                        }
//                        System.out.println(Taxonomy_number);
                        rs1.close();
                        String getSqlP = "select number from germplasm.provenance where " +
                                "Continent = ? AND Continent_abbr = ? AND Country = ? AND " +
                                "Country_abbr = ? AND City = ?";
                        PreparedStatement getP = conn.prepareStatement(getSqlP);
                        getP.setString(1, Continent);
                        getP.setString(2, Continent_Abbreviate);
                        getP.setString(3, Country);
                        getP.setString(4, Country_Abbreviate);
                        getP.setString(5, City_USDA);
                        ResultSet rs2 = getP.executeQuery();
                        if (rs2.next()) {
                            Provenance_number = rs2.getInt(1);
                        }
//                        System.out.println(Provenance_number);
                        rs2.close();
                        // get Accession
                        String getSql = "select Accession from germplasm.germplasm where " +
                                "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                                "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                                "Longitude = ? and Elevation = ? and Introduced_year = ? and Eco_region = ?";
                        PreparedStatement getStmt = conn.prepareStatement(getSql);
                        getStmt.setString(1, English_name);
                        getStmt.setString(2, Sample_name);
                        getStmt.setString(3, Chinese_name);
                        getStmt.setString(4, Type_final);
                        getStmt.setInt(5, Taxonomy_number);
                        getStmt.setString(6, Pedigree);
                        getStmt.setString(7, Growth_habit);
                        getStmt.setInt(8, Provenance_number);
                        getStmt.setDouble(9, Latitude);
                        getStmt.setDouble(10, Longitude);
                        getStmt.setDouble(11, Elevation);
                        getStmt.setString(12, Released_or_introduced_year);
                        getStmt.setString(13, Eco_region);
                        ResultSet rs3 = getStmt.executeQuery();
                        if (rs3.next()) {
                            Accession = rs3.getString(1);
                        }
                        // check repeat
                        String checkSql = "SELECT COUNT(*) FROM germplasm.inherit WHERE Accession = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                        checkStmt.setString(1, Accession);
                        ResultSet rs = checkStmt.executeQuery();
                        rs.next();
                        // insert data
                        if (rs.getInt(1) == 0) {
                            String sql = "insert into germplasm.inherit " +
                                    "(Accession, USDA, IGDB, WGD_V7, CGRIS, USA, CHN, NSGC, CAAS, HENU, UCDAVIS) " +
                                    "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            pS = conn.prepareStatement(sql);
                            pS.setString(1, Accession);
                            pS.setBoolean(2, USDA);
                            pS.setBoolean(3, IGDB);
                            pS.setBoolean(4, WGD_V7);
                            pS.setBoolean(5, CGRIS);
                            pS.setBoolean(6, USA);
                            pS.setBoolean(7, CHN);
                            pS.setBoolean(8, NSGC);
                            pS.setBoolean(9, CAAS);
                            pS.setBoolean(10, HENU);
                            pS.setBoolean(11, UCDAVIS);
                            pS.executeUpdate();
                        }
                    }
                    if (table.equals("outlib")) {
                        // get key numbers
                        int Taxonomy_number = 0;
                        int Provenance_number = 0;
                        String getSqlT = "select number from germplasm.taxonomy where " +
                                "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                                "Common_name = ? AND Genome_type = ?";
                        PreparedStatement getT = conn.prepareStatement(getSqlT);
                        getT.setString(1, Genus);
                        getT.setString(2, Species);
                        getT.setString(3, Full_Taxonomy);
                        getT.setString(4, Common_name);
                        getT.setString(5, Genome_type);
                        ResultSet rs1 = getT.executeQuery();
                        if (rs1.next()) {
                            Taxonomy_number = rs1.getInt(1);
                        }
//                        System.out.println(Taxonomy_number);
                        rs1.close();
                        String getSqlP = "select number from germplasm.provenance where " +
                                "Continent = ? AND Continent_abbr = ? AND Country = ? AND " +
                                "Country_abbr = ? AND City = ?";
                        PreparedStatement getP = conn.prepareStatement(getSqlP);
                        getP.setString(1, Continent);
                        getP.setString(2, Continent_Abbreviate);
                        getP.setString(3, Country);
                        getP.setString(4, Country_Abbreviate);
                        getP.setString(5, City_USDA);
                        ResultSet rs2 = getP.executeQuery();
                        if (rs2.next()) {
                            Provenance_number = rs2.getInt(1);
                        }
//                        System.out.println(Provenance_number);
                        rs2.close();
                        // get Accession
                        String getSql = "select Accession from germplasm.germplasm where " +
                                "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                                "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                                "Longitude = ? and Elevation = ? and Introduced_year = ? and Eco_region = ?";
                        PreparedStatement getStmt = conn.prepareStatement(getSql);
                        getStmt.setString(1, English_name);
                        getStmt.setString(2, Sample_name);
                        getStmt.setString(3, Chinese_name);
                        getStmt.setString(4, Type_final);
                        getStmt.setInt(5, Taxonomy_number);
                        getStmt.setString(6, Pedigree);
                        getStmt.setString(7, Growth_habit);
                        getStmt.setInt(8, Provenance_number);
                        getStmt.setDouble(9, Latitude);
                        getStmt.setDouble(10, Longitude);
                        getStmt.setDouble(11, Elevation);
                        getStmt.setString(12, Released_or_introduced_year);
                        getStmt.setString(13, Eco_region);
                        ResultSet rs3 = getStmt.executeQuery();
                        if (rs3.next()) {
                            Accession = rs3.getString(1);
                        }
                        String getDB = "select USDA, IGDB, WGD_V7 from germplasm.inherit where Accession = ?";
                        PreparedStatement getStmtDB = conn.prepareStatement(getDB);
                        getStmtDB.setString(1, Accession);
                        ResultSet exist_lib = getStmtDB.executeQuery();
                        exist_lib.next();
                        if (exist_lib.getBoolean(1)) {
                            // 1. insert USDA
                            String checkSql_usda = "SELECT COUNT(*) FROM germplasm.usda WHERE Accession = ?";
                            PreparedStatement checkStmt_usda = conn.prepareStatement(checkSql_usda);
                            checkStmt_usda.setString(1, Accession);
                            ResultSet rs_usda = checkStmt_usda.executeQuery();
                            rs_usda.next();
                            if (rs_usda.getInt(1) == 0) {
                                String sql = "insert into germplasm.usda " +
                                        "(Accession, Wheat_type, City, Received_year, Source_date) " +
                                        "value (?, ?, ?, ?, ?)";
                                pS = conn.prepareStatement(sql);
                                pS.setString(1, Accession);
                                pS.setString(2, Type_USDA8);
                                pS.setString(3, City_USDA);
                                pS.setString(4, Received_year_USDA);
                                pS.setString(5, Source_data_USDA);
                                pS.executeUpdate();
                            }
                        }
                        if (exist_lib.getBoolean(2)) {
                            // 2. insert IGDB
                            String checkSql_igdb = "SELECT COUNT(*) FROM germplasm.igdb WHERE Accession = ?";
                            PreparedStatement checkStmt_igdb = conn.prepareStatement(checkSql_igdb);
                            checkStmt_igdb.setString(1, Accession);
                            ResultSet rs_igdb = checkStmt_igdb.executeQuery();
                            rs_igdb.next();
                            if (rs_igdb.getInt(1) == 0) {
                                String sql = "insert into germplasm.igdb " +
                                        "(Accession, Chinese_name, Wheat_type, Growth_habit) " +
                                        "value (?, ?, ?, ?)";
                                pS = conn.prepareStatement(sql);
                                pS.setString(1, Accession);
                                pS.setString(2, Chinese_name);
                                pS.setString(3, Type_original8);
                                pS.setString(4, Growth_class);
                                pS.executeUpdate();
                            }
                        }
                        if (exist_lib.getBoolean(3)) {
                            // 3. insert WGD_V7
                            String checkSql_wgd = "SELECT COUNT(*) FROM germplasm.wgd_v7 WHERE Accession = ?";
                            PreparedStatement checkStmt_wgd = conn.prepareStatement(checkSql_wgd);
                            checkStmt_wgd.setString(1, Accession);
                            ResultSet rs_wgd = checkStmt_wgd.executeQuery();
                            rs_wgd.next();
                            if (rs_wgd.getInt(1) == 0) {
                                String sql = "insert into germplasm.wgd_v7 " +
                                        "(Accession, ID_yin, Common_name) " +
                                        "value (?, ?, ?)";
                                pS = conn.prepareStatement(sql);
                                pS.setString(1, Accession);
                                pS.setString(2, ID_yin);
                                pS.setString(3, Common_name);
                                pS.executeUpdate();
                            }
                        }
                    }
                }
//                pS.executeBatch();
                System.out.println(pglNumber);
                conn.commit();
                workbook.close();
            }
            if (source == "as") readTable_AS(conn, pS, table, sheet_num, pglNumber);
            if (source == "vmap4") readTable_V4(conn, pS, table, sheet_num, pglNumber);

            System.out.println("Data import successfully!");
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (pS != null) pS.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void readTable_AS(Connection conn, PreparedStatement pS, String table, int sheet_num, int pglNumber) throws SQLException, IOException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        FileInputStream file = new FileInputStream(new File("V4DB/src/main/resources/V4_germplasm/A&Sgenome.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(sheet_num);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            String GID = getCellValueAsString(row.getCell(0));
            String Bams = GID.substring(0, 1) + "_" + GID.substring(1) + ".bam";
            String Accession = getCellValueAsString(row.getCell(1));
            String Data_sources = getCellValueAsString(row.getCell(2));
            String Genus = getCellValueAsString(row.getCell(3));
            String Species = getCellValueAsString(row.getCell(4));
            String Full_Taxonomy = getCellValueAsString(row.getCell(5));
            String Country = getCellValueAsString(row.getCell(6));
            double Latitude = getCellValueAsDouble(row.getCell(7));
            double Longitude = getCellValueAsDouble(row.getCell(8));
            String Genome_type = getCellValueAsString(row.getCell(9));
            String Continent_Abbreviate = getCellValueAsString(row.getCell(10));
            String Common_name = getCellValueAsString(row.getCell(11));
            String Growth_habit = getCellValueAsString(row.getCell(12));
            int GRepeat = 0;
            if (table.equals("taxonomy")) {
                // no repeat Taxonomy
                String checkSql = "SELECT COUNT(*) FROM germplasm.taxonomy WHERE " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Genus);
                checkStmt.setString(2, Species);
                checkStmt.setString(3, Full_Taxonomy);
                checkStmt.setString(4, Common_name);
                checkStmt.setString(5, Genome_type);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
//                        System.out.println(rs.getInt(1));
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.taxonomy" +
                            "(Genus, Species, Full_Taxonomy, Common_name, Genome_type, Ploidy)" +
                            "values(?, ?, ?, ?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Genus);
                    pS.setString(2, Species);
                    pS.setString(3, Full_Taxonomy);
                    pS.setString(4, Common_name);
                    pS.setString(5, Genome_type);
                    pS.setInt(6, 2);
                    pS.executeUpdate();
                }
                rs.close();
                checkStmt.close();
            }
            if (table.equals("provenance")) {
                // check no repeat Provenance
                String checkSql = "SELECT COUNT(*) FROM germplasm.provenance WHERE " +
                        "Continent_abbr = ? AND Country = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Continent_Abbreviate);
                checkStmt.setString(2, Country);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.provenance" +
                            "(Continent_abbr, Country)" +
                            "value(?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Continent_Abbreviate);
                    pS.setString(2, Country);
                    pS.executeUpdate();
                }
                rs.close();
                checkStmt.close();
            }
            if (table.equals("germplasm")) {
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Full_Taxonomy);
                getT.setString(4, Common_name);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where Continent_abbr = ? AND Country = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Continent_Abbreviate);
                getP.setString(2, Country);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // check accession
                String checkSql = "SELECT COUNT(*) FROM germplasm.germplasm WHERE Accession = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Accession);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (Accession.equals("-") || Accession.equals("NA") || Accession.isEmpty()) {
                    String getSql = "select count(*) from germplasm.germplasm where " +
                            "Taxonomy_number = ? and Growth_habit = ? and Provenance_number = ? and " +
                            "Latitude = ? and Longitude = ?";
                    PreparedStatement getStmt = conn.prepareStatement(getSql);
                    getStmt.setInt(1, Taxonomy_number);
                    getStmt.setString(2, Growth_habit);
                    getStmt.setInt(3, Provenance_number);
                    getStmt.setDouble(4, Latitude);
                    getStmt.setDouble(5, Longitude);
                    ResultSet rs3 = getStmt.executeQuery();
                    rs3.next();
                    if (rs3.getInt(1) == 0) {
                        String PGL_accession = "PGL" + String.format("%05d", pglNumber);
                        pglNumber++;
//                            System.out.println(pglNumber);
                        String sql = "insert into germplasm.germplasm " +
                                "(Accession, Taxonomy_number, Growth_habit, Provenance_number, " +
                                "Latitude, Longitude) " +
                                "value (?, ?, ?, ?, ?, ?)";
                        pS = conn.prepareStatement(sql);
                        pS.setString(1, PGL_accession);
                        pS.setInt(2, Taxonomy_number);
                        pS.setString(3, Growth_habit);
                        pS.setInt(4, Provenance_number);
                        pS.setDouble(5, Latitude);
                        pS.setDouble(6, Longitude);
                        pS.executeUpdate();
                    }
                } else if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.germplasm " +
                            "(Accession, Taxonomy_number, Growth_habit, Provenance_number, " +
                            "Latitude, Longitude) " +
                            "value (?, ?, ?, ?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Accession);
                    pS.setInt(2, Taxonomy_number);
                    pS.setString(3, Growth_habit);
                    pS.setInt(4, Provenance_number);
                    pS.setDouble(5, Latitude);
                    pS.setDouble(6, Longitude);
                    pS.executeUpdate();
                }
            }
            if (table.equals("wgs")) {
                // check accession
                String checkSql = "SELECT COUNT(*) FROM germplasm.wgs WHERE GID = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, GID);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.wgs" +
                            "(GID, Bams, Dataset)" +
                            "values(?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, GID);
                    pS.setString(2, Bams);
                    pS.setString(3, Data_sources);
                    pS.executeUpdate();
                }
            }
            if (table.equals("experiment")) {
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Full_Taxonomy);
                getT.setString(4, Common_name);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where " +
                        "Continent_abbr = ? AND Country = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Continent_Abbreviate);
                getP.setString(2, Country);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // get Accession
                String getSql = "select Accession from germplasm.germplasm where " +
                        "Taxonomy_number = ? and Growth_habit = ? and Provenance_number = ? and " +
                        "Latitude = ? and Longitude = ?";
                PreparedStatement getStmt = conn.prepareStatement(getSql);
                getStmt.setInt(1, Taxonomy_number);
                getStmt.setString(2, Growth_habit);
                getStmt.setInt(3, Provenance_number);
                getStmt.setDouble(4, Latitude);
                getStmt.setDouble(5, Longitude);
                ResultSet rs3 = getStmt.executeQuery();
                if (rs3.next()) {
                    Accession = rs3.getString(1);
                }
                // identify GRepeat
                String repeatSql = "SELECT COUNT(*) FROM germplasm.experiment WHERE Accession = ?";
                PreparedStatement repeatStmt = conn.prepareStatement(repeatSql);
                repeatStmt.setString(1, Accession);
                ResultSet rs4 = repeatStmt.executeQuery();
                rs4.next();
                GRepeat = rs4.getInt(1) + 1;
                // check GID
                String checkSql = "SELECT COUNT(*) FROM germplasm.experiment WHERE GID = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, GID);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.experiment " +
                            "(Accession, GID, GRepeat, Data_sources) " +
                            "value (?, ?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Accession);
                    pS.setString(2, GID);
                    pS.setInt(3, GRepeat);
                    pS.setString(4, "LuLab");
                    pS.executeUpdate();
                }
            }
            if (table.equals("inherit")) {
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Full_Taxonomy);
                getT.setString(4, Common_name);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where " +
                        "Continent_abbr = ? AND Country = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Continent_Abbreviate);
                getP.setString(2, Country);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // get Accession
                String getSql = "select Accession from germplasm.germplasm where " +
                        "Taxonomy_number = ? and Growth_habit = ? and Provenance_number = ? and " +
                        "Latitude = ? and Longitude = ?";
                PreparedStatement getStmt = conn.prepareStatement(getSql);
                getStmt.setInt(1, Taxonomy_number);
                getStmt.setString(2, Growth_habit);
                getStmt.setInt(3, Provenance_number);
                getStmt.setDouble(4, Latitude);
                getStmt.setDouble(5, Longitude);
                ResultSet rs3 = getStmt.executeQuery();
                if (rs3.next()) {
                    Accession = rs3.getString(1);
                }
                // check repeat
                String checkSql = "SELECT COUNT(*) FROM germplasm.inherit WHERE Accession = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Accession);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.inherit " +
                            "(Accession) " +
                            "value (?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Accession);
                    pS.executeUpdate();
                }
            }
        }
//                pS.executeBatch();
        System.out.println(pglNumber);
        conn.commit();
        workbook.close();
    }

    public static void readTable_wiews(Connection conn, PreparedStatement pS, String table, int sheet_num) throws SQLException, IOException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        FileInputStream file = new FileInputStream(new File("V4DB/src/main/resources/wiews_wheat.csv"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(sheet_num);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            String Update_year
            String Country_ISO3_code
            String Country_name
            String Holding_institute_code
            String Holding_institute_name
            String Accession_number
            String Taxon
            String Genus
            String Species
            String Accepted_Genus
            String Accepted_Species
            String Crop_name
            String Acquisition date (YYYY/MM)
            String Country of origin (ISO3)
            String Country of origin
            String Biological status
            String Genebank(s) holding safety duplications - code
            String Genebank(s) holding safety duplications
            String Latitude of collecting site (decimal degrees format)
            String Longitude of collecting site (decimal degrees format)
            String Collecting/acquisition source
            String Type of germplasm storage
            String Status under the Multilateral System
            String DOI
            String Data owner
            String Data owner details
            String Source of information

        }
    }

    public static void readTable_V4(Connection conn, PreparedStatement pS, String table, int sheet_num, int pglNumber) throws SQLException, IOException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        FileInputStream file = new FileInputStream(new File("V4DB/src/main/resources/V4_germplasm/V4_germplasm.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(sheet_num);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            String GID = getCellValueAsString(row.getCell(0));
//            String GRepeat = getCellValueAsString(row.getCell(1));
            String Accession = getCellValueAsString(row.getCell(2));
            String Chinese_nam = getCellValueAsString(row.getCell(3));
            String English_nam = getCellValueAsString(row.getCell(4));
            String Accession_name_GS = getCellValueAsString(row.getCell(5));
            String Genome_type = getCellValueAsString(row.getCell(6));
            Genome_type = switch (Genome_type) {
                case "AB" -> "AABB";
                case "ABD" -> "AABBDD";
                case "AABG" -> "AAAABBGG";
                default -> Genome_type;
            };
            int Ploidy = Genome_type.length();
            String Pedigree = getCellValueAsString(row.getCell(8));
            String Genus = getCellValueAsString(row.getCell(9));
            String Species = getCellValueAsString(row.getCell(10));
            String Taxonomy_update_GS = getCellValueAsString(row.getCell(11));
            String Simple_classification = getCellValueAsString(row.getCell(12));
            String Group_yin = getCellValueAsString(row.getCell(13));
            String Biological_status_of_accession_upadate_GS = getCellValueAsString(row.getCell(14));
            String Growth_class = getCellValueAsString(row.getCell(15));
            String Provenance_of_material_GS = getCellValueAsString(row.getCell(16));
            String Country = getCellValueAsString(row.getCell(17));
            String Province = getCellValueAsString(row.getCell(18));
            String Acquisition_date_GS = getCellValueAsString(row.getCell(19));
            String Location_of_collecting_site_GS = getCellValueAsString(row.getCell(20));
            double Latitude_GS = getCellValueAsDouble(row.getCell(21));
            double Longitude_GS = getCellValueAsDouble(row.getCell(22));
            double Elevation_GS = getCellValueAsDouble(row.getCell(23));
            String R1 = getCellValueAsString(row.getCell(24));
            String WAP2085_to_2588 = getCellValueAsString(row.getCell(25));
            String R_and_S = getCellValueAsString(row.getCell(26));
            String Genesys_UR = getCellValueAsString(row.getCell(27));
            int GRepeat = 0;
            if (table.equals("taxonomy")) {
                // no repeat Taxonomy
                String checkSql = "SELECT COUNT(*) FROM germplasm.taxonomy WHERE " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Genus);
                checkStmt.setString(2, Species);
                checkStmt.setString(3, Taxonomy_update_GS);
                checkStmt.setString(4, Group_yin);
                checkStmt.setString(5, Genome_type);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
//                        System.out.println(rs.getInt(1));
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.taxonomy" +
                            "(Genus, Species, Full_Taxonomy, Common_name, Genome_type, Ploidy)" +
                            "values(?, ?, ?, ?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Genus);
                    pS.setString(2, Species);
                    pS.setString(3, Taxonomy_update_GS);
                    pS.setString(4, Group_yin);
                    pS.setString(5, Genome_type);
                    pS.setInt(6, Ploidy);
                    pS.executeUpdate();
                }
                rs.close();
                checkStmt.close();
            }
            if (table.equals("provenance")) {
                // check no repeat Provenance
                String checkSql = "SELECT COUNT(*) FROM germplasm.provenance WHERE " +
                        "Country = ? AND Province = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Provenance_of_material_GS);
                checkStmt.setString(2, Province);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.provenance" +
                            "(Country, Province)" +
                            "value(?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Provenance_of_material_GS);
                    pS.setString(2, Province);
                    pS.executeUpdate();
                }
                rs.close();
                checkStmt.close();
            }
            if (table.equals("germplasm")) {
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Taxonomy_update_GS);
                getT.setString(4, Group_yin);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where " +
                        "Country = ? AND Province = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Provenance_of_material_GS);
                getP.setString(2, Province);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // check accession
                String checkSql = "SELECT COUNT(*) FROM germplasm.germplasm WHERE Accession = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Accession);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (Accession.equals("-") || Accession.equals("NA") || Accession.isEmpty()) {
                    String getSql = "select count(*) from germplasm.germplasm where " +
                            "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                            "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                            "Longitude = ? and Elevation = ? and Introduced_year = ?";
                    PreparedStatement getStmt = conn.prepareStatement(getSql);
                    getStmt.setString(1, Accession_name_GS);
                    getStmt.setString(2, English_nam);
                    getStmt.setString(3, Chinese_nam);
                    getStmt.setString(4, Biological_status_of_accession_upadate_GS);
                    getStmt.setInt(5, Taxonomy_number);
                    getStmt.setString(6, Pedigree);
                    getStmt.setString(7, Growth_class);
                    getStmt.setInt(8, Provenance_number);
                    getStmt.setDouble(9, Latitude_GS);
                    getStmt.setDouble(10, Longitude_GS);
                    getStmt.setDouble(11, Elevation_GS);
                    getStmt.setString(12, Acquisition_date_GS);
                    ResultSet rs3 = getStmt.executeQuery();
                    rs3.next();
                    if (rs3.getInt(1) == 0) {
                        String PGL_accession = "PGL" + String.format("%05d", pglNumber);
                        pglNumber++;
//                            System.out.println(pglNumber);
                        String sql = "insert into germplasm.germplasm " +
                                "(Accession, Name, Other_name, Chinese_name, Wheat_type, " +
                                "Taxonomy_number, Pedigree, Growth_habit, Provenance_number, Latitude, " +
                                "Longitude, Elevation, Introduced_year) " +
                                "value (?, ?, ?, ?, ?, " +
                                "?, ?, ?, ?, ?, " +
                                "?, ?, ?)";
                        pS = conn.prepareStatement(sql);
                        pS.setString(1, PGL_accession);
                        pS.setString(2, Accession_name_GS);
                        pS.setString(3, English_nam);
                        pS.setString(4, Chinese_nam);
                        pS.setString(5, Biological_status_of_accession_upadate_GS);
                        pS.setInt(6, Taxonomy_number);
                        pS.setString(7, Pedigree);
                        pS.setString(8, Growth_class);
                        pS.setInt(9, Provenance_number);
                        pS.setDouble(10, Latitude_GS);
                        pS.setDouble(11, Longitude_GS);
                        pS.setDouble(12, Elevation_GS);
                        pS.setString(13, Acquisition_date_GS);
                        pS.executeUpdate();
                    }
                } else if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.germplasm " +
                            "(Accession, Name, Other_name, Chinese_name, Wheat_type, " +
                            "Taxonomy_number, Pedigree, Growth_habit, Provenance_number, Latitude, " +
                            "Longitude, Elevation, Introduced_year) " +
                            "value (?, ?, ?, ?, ?, " +
                            "?, ?, ?, ?, ?, " +
                            "?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Accession);
                    pS.setString(2, Accession_name_GS);
                    pS.setString(3, English_nam);
                    pS.setString(4, Chinese_nam);
                    pS.setString(5, Biological_status_of_accession_upadate_GS);
                    pS.setInt(6, Taxonomy_number);
                    pS.setString(7, Pedigree);
                    pS.setString(8, Growth_class);
                    pS.setInt(9, Provenance_number);
                    pS.setDouble(10, Latitude_GS);
                    pS.setDouble(11, Longitude_GS);
                    pS.setDouble(12, Elevation_GS);
                    pS.setString(13, Acquisition_date_GS);
                    pS.executeUpdate();
                }
            }
            if (table.equals("wgs")) {
                // check accession
                String checkSql = "SELECT COUNT(*) FROM germplasm.wgs WHERE GID = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, GID);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.wgs" +
                            "(GID, Dataset)" +
                            "values(?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, GID);
                    pS.setString(2, "vmap4");
                    pS.executeUpdate();
                }
            }
            if (table.equals("experiment")) {
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Taxonomy_update_GS);
                getT.setString(4, Group_yin);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where " +
                        "Country = ? AND Province = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Provenance_of_material_GS);
                getP.setString(2, Province);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // get Accession
                String getSql = "select Accession from germplasm.germplasm where " +
                        "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                        "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                        "Longitude = ? and Elevation = ? and Introduced_year = ?";
                PreparedStatement getStmt = conn.prepareStatement(getSql);
                getStmt.setString(1, Accession_name_GS);
                getStmt.setString(2, English_nam);
                getStmt.setString(3, Chinese_nam);
                getStmt.setString(4, Biological_status_of_accession_upadate_GS);
                getStmt.setInt(5, Taxonomy_number);
                getStmt.setString(6, Pedigree);
                getStmt.setString(7, Growth_class);
                getStmt.setInt(8, Provenance_number);
                getStmt.setDouble(9, Latitude_GS);
                getStmt.setDouble(10, Longitude_GS);
                getStmt.setDouble(11, Elevation_GS);
                getStmt.setString(12, Acquisition_date_GS);
                ResultSet rs3 = getStmt.executeQuery();
                if (rs3.next()) {
                    Accession = rs3.getString(1);
                }
                // identify GRepeat
                String repeatSql = "SELECT COUNT(*) FROM germplasm.experiment WHERE Accession = ?";
                PreparedStatement repeatStmt = conn.prepareStatement(repeatSql);
                repeatStmt.setString(1, Accession);
                ResultSet rs4 = repeatStmt.executeQuery();
                rs4.next();
                GRepeat = rs4.getInt(1) + 1;
                // check GID
                String checkSql = "SELECT COUNT(*) FROM germplasm.experiment WHERE GID = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, GID);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.experiment " +
                            "(Accession, GID, GRepeat, Data_sources, R1, WAP2085_to_2588, R_and_S) " +
                            "value (?, ?, ?, ?, ?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Accession);
                    pS.setString(2, GID);
                    pS.setInt(3, GRepeat);
                    pS.setString(4, "LuLab");
                    pS.setString(5, R1);
                    pS.setString(6, WAP2085_to_2588);
                    pS.setString(7, R_and_S);
                    pS.executeUpdate();
                }
            }
            if (table.equals("inherit")) {
                boolean Genesys = !Accession.isEmpty()&&!Accession.equals("NA");
                boolean IGDB = !Chinese_nam.isEmpty();
                boolean WGD_V7 = !Group_yin.isEmpty()&&!Group_yin.equals("-");
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Taxonomy_update_GS);
                getT.setString(4, Group_yin);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where " +
                        "Country = ? AND Province = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Provenance_of_material_GS);
                getP.setString(2, Province);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // get Accession
                String getSql = "select Accession from germplasm.germplasm where " +
                        "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                        "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                        "Longitude = ? and Elevation = ? and Introduced_year = ?";
                PreparedStatement getStmt = conn.prepareStatement(getSql);
                getStmt.setString(1, Accession_name_GS);
                getStmt.setString(2, English_nam);
                getStmt.setString(3, Chinese_nam);
                getStmt.setString(4, Biological_status_of_accession_upadate_GS);
                getStmt.setInt(5, Taxonomy_number);
                getStmt.setString(6, Pedigree);
                getStmt.setString(7, Growth_class);
                getStmt.setInt(8, Provenance_number);
                getStmt.setDouble(9, Latitude_GS);
                getStmt.setDouble(10, Longitude_GS);
                getStmt.setDouble(11, Elevation_GS);
                getStmt.setString(12, Acquisition_date_GS);
                ResultSet rs3 = getStmt.executeQuery();
                if (rs3.next()) {
                    Accession = rs3.getString(1);
                }
                // check repeat
                String checkSql = "SELECT COUNT(*) FROM germplasm.inherit WHERE Accession = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, Accession);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                // insert data
                if (rs.getInt(1) == 0) {
                    String sql = "insert into germplasm.inherit " +
                            "(Accession, Genesys, IGDB, WGD_V7) " +
                            "value (?, ?, ?, ?)";
                    pS = conn.prepareStatement(sql);
                    pS.setString(1, Accession);
                    pS.setBoolean(2, Genesys);
                    pS.setBoolean(3, IGDB);
                    pS.setBoolean(4, WGD_V7);
                    pS.executeUpdate();
                }
            }
            if (table.equals("outlib")) {
                // get key numbers
                int Taxonomy_number = 0;
                int Provenance_number = 0;
                String getSqlT = "select number from germplasm.taxonomy where " +
                        "Genus = ? AND Species = ? AND Full_Taxonomy = ? AND " +
                        "Common_name = ? AND Genome_type = ?";
                PreparedStatement getT = conn.prepareStatement(getSqlT);
                getT.setString(1, Genus);
                getT.setString(2, Species);
                getT.setString(3, Taxonomy_update_GS);
                getT.setString(4, Group_yin);
                getT.setString(5, Genome_type);
                ResultSet rs1 = getT.executeQuery();
                if (rs1.next()) {
                    Taxonomy_number = rs1.getInt(1);
                }
//                        System.out.println(Taxonomy_number);
                rs1.close();
                String getSqlP = "select number from germplasm.provenance where " +
                        "Country = ? AND Province = ?";
                PreparedStatement getP = conn.prepareStatement(getSqlP);
                getP.setString(1, Provenance_of_material_GS);
                getP.setString(2, Province);
                ResultSet rs2 = getP.executeQuery();
                if (rs2.next()) {
                    Provenance_number = rs2.getInt(1);
                }
//                        System.out.println(Provenance_number);
                rs2.close();
                // get Accession
                String getSql = "select Accession from germplasm.germplasm where " +
                        "Name = ? and Other_name = ? and Chinese_name = ? and Wheat_type = ? and Taxonomy_number = ? and " +
                        "Pedigree = ? and Growth_habit = ? and Provenance_number = ? and Latitude = ? and " +
                        "Longitude = ? and Elevation = ? and Introduced_year = ?";
                PreparedStatement getStmt = conn.prepareStatement(getSql);
                getStmt.setString(1, Accession_name_GS);
                getStmt.setString(2, English_nam);
                getStmt.setString(3, Chinese_nam);
                getStmt.setString(4, Biological_status_of_accession_upadate_GS);
                getStmt.setInt(5, Taxonomy_number);
                getStmt.setString(6, Pedigree);
                getStmt.setString(7, Growth_class);
                getStmt.setInt(8, Provenance_number);
                getStmt.setDouble(9, Latitude_GS);
                getStmt.setDouble(10, Longitude_GS);
                getStmt.setDouble(11, Elevation_GS);
                getStmt.setString(12, Acquisition_date_GS);
                ResultSet rs3 = getStmt.executeQuery();
                if (rs3.next()) {
                    Accession = rs3.getString(1);
                }
                String getDB = "select Genesys, IGDB, WGD_V7 from germplasm.inherit where Accession = ?";
                PreparedStatement getStmtDB = conn.prepareStatement(getDB);
                getStmtDB.setString(1, Accession);
                ResultSet exist_lib = getStmtDB.executeQuery();
                exist_lib.next();
                if (exist_lib.getBoolean(1)) {
                    // 1. insert Genesys
                    String checkSql_usda = "SELECT COUNT(*) FROM germplasm.genesys WHERE Accession = ?";
                    PreparedStatement checkStmt_usda = conn.prepareStatement(checkSql_usda);
                    checkStmt_usda.setString(1, Accession);
                    ResultSet rs_usda = checkStmt_usda.executeQuery();
                    rs_usda.next();
                    if (rs_usda.getInt(1) == 0) {
                        String sql = "insert into germplasm.genesys " +
                                "(Accession, Accession_name, Taxonomy, Biological_status_of_accession, " +
                                "Provenance_of_material, Acquisition_date, Location_of_collecting_site, " +
                                "Latitude, Longitude, Elevation, URL) " +
                                "value (?, ?, ?, ?, " +
                                "?, ?, ?, " +
                                "?, ?, ?, ?)";
                        pS = conn.prepareStatement(sql);
                        pS.setString(1, Accession);
                        pS.setString(2, Accession_name_GS);
                        pS.setString(3, Taxonomy_update_GS);
                        pS.setString(4, Biological_status_of_accession_upadate_GS);
                        pS.setString(5, Provenance_of_material_GS);
                        pS.setString(6, Acquisition_date_GS);
                        pS.setString(7, Location_of_collecting_site_GS);
                        pS.setDouble(8, Latitude_GS);
                        pS.setDouble(9, Longitude_GS);
                        pS.setDouble(10, Elevation_GS);
                        pS.setString(11, Genesys_UR);
                        pS.executeUpdate();
                    }
                }
                if (exist_lib.getBoolean(2)) {
                    // 2. insert IGDB
                    String checkSql_igdb = "SELECT COUNT(*) FROM germplasm.igdb WHERE Accession = ?";
                    PreparedStatement checkStmt_igdb = conn.prepareStatement(checkSql_igdb);
                    checkStmt_igdb.setString(1, Accession);
                    ResultSet rs_igdb = checkStmt_igdb.executeQuery();
                    rs_igdb.next();
                    if (rs_igdb.getInt(1) == 0) {
                        String sql = "insert into germplasm.igdb " +
                                "(Accession, Chinese_name, Growth_habit) " +
                                "value (?, ?, ?)";
                        pS = conn.prepareStatement(sql);
                        pS.setString(1, Accession);
                        pS.setString(2, Chinese_nam);
                        pS.setString(3, Growth_class);
                        pS.executeUpdate();
                    }
                }
                if (exist_lib.getBoolean(3)) {
                    // 3. insert WGD_V7
                    String checkSql_wgd = "SELECT COUNT(*) FROM germplasm.wgd_v7 WHERE Accession = ?";
                    PreparedStatement checkStmt_wgd = conn.prepareStatement(checkSql_wgd);
                    checkStmt_wgd.setString(1, Accession);
                    ResultSet rs_wgd = checkStmt_wgd.executeQuery();
                    rs_wgd.next();
                    if (rs_wgd.getInt(1) == 0) {
                        String sql = "insert into germplasm.wgd_v7 " +
                                "(Accession, Group_yin, Common_name) " +
                                "value (?, ?, ?)";
                        pS = conn.prepareStatement(sql);
                        pS.setString(1, Accession);
                        pS.setString(2, Group_yin);
                        pS.setString(3, Simple_classification);
                        pS.executeUpdate();
                    }
                }
            }
        }
        System.out.println(pglNumber);
        conn.commit();
        workbook.close();
    }
}



