/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import DataSources.DataProcessor;
import ExceptionHandler.ModuleCodeInvalidException;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class DataProcessorTest {
    public static void main(String args[]) throws ModuleCodeInvalidException{
        DataProcessor dp = new DataProcessor("c:/classlist.xlsx", "c:/ce01096-5marks.xls", "c:/ce01096-sesi.xls");
        ArrayList<String[]> classData = new ArrayList();
        classData = dp.getClassList();
        for(String[] s:classData){
            for(int i = 0;i<s.length;i++){
                System.out.println(s[i]);
            }
        }
    }
}
