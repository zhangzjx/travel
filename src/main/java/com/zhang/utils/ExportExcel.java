package com.zhang.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ExportExcel {
 
    //显示的导出表的标题
    private String title;
    //导出表的列名
    private String[] rowName ;
    //导出表的数据
    private List<Object[]>  dataList = new ArrayList<Object[]>();
 
    HttpServletResponse  response;
 
    //构造方法，传入要导出的数据
    public ExportExcel(String title, String[] rowName, List<Object[]> dataList){
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }
 
    /**
     * 导出数据
     * */
    public void export(OutputStream out) throws Exception{
        try{
            HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象
            HSSFSheet sheet = workbook.createSheet(title);                  // 创建工作表
 
            // 产生表格标题行
            // HSSFRow rowm = sheet.createRow(0);
            // HSSFCell cellTiltle = rowm.createCell(0);
 
            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象
 
    //          sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));//合并单元格  
    //          cellTiltle.setCellStyle(columnTopStyle);
    //          cellTiltle.setCellValue(title);
 
            // 定义所需列数
            int columnNum = rowName.length;
            // 在索引2的位置创建行(最顶端的行开始的第二行)
            HSSFRow rowRowName = sheet.createRow(0);
 
            // 将列头设置到sheet的单元格中
            for(int n=0;n<columnNum;n++){
                //创建列头对应个数的单元格
                HSSFCell  cellRowName = rowRowName.createCell(n);
               // cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                //设置列头单元格的值
                //cellRowName.setCellValue(text);
                cellRowName.setCellStyle(columnTopStyle);
            }
 
            //将查询出的数据设置到sheet对应的单元格中
            for(int i=0;i<dataList.size();i++){
                //遍历每个对象
                Object[] obj = dataList.get(i);
                //创建所需的行数（从第二行开始写数据）
                HSSFRow row = sheet.createRow(i+1);
 
                for(int j=0; j<obj.length; j++){
                    //设置单元格的数据类型
                    HSSFCell  cell = null;
                    if(j == 0){
                        cell = row.createCell(j, CellType.STRING);
                        //cell.setCellValue(i+1);
                    }else{
                        cell = row.createCell(j,CellType.STRING);
                        if(!"".equals(obj[j]) && obj[j] != null){
                            //设置单元格的值
                            //cell.setCellValue(obj[j].toString());
                        }
                    }
                    //设置单元格样式
                    cell.setCellStyle(style);
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
     //                 if (currentRow.getCell(colNum) != null) {
     //                     HSSFCell currentCell = currentRow.getCell(colNum);
    //                      if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
    //                          int length =     currentCell.getStringCellValue().getBytes().length;
    //                          if (columnWidth < length) {
    //                              columnWidth = length;
    //                          }
    //                      }
    //                  }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == CellType.STRING) {
                            int length = 0;
                            try {
                                length = currentCell.getStringCellValue().getBytes().length;
                            } catch (Exception e) {
                                //e.printStackTrace();
                            }
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
 
                }
                if(colNum == 0){
                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                }else{
                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                }
            }
            if(workbook !=null){
                try{
                   workbook.write(out);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
 
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            out.close();
        }
    }

    /**
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();

        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行; HorizontalAlignment
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);

        return style;

    }

    /**
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();

        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行; HorizontalAlignment
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);

        return style;

    }
}
