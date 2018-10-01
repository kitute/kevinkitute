/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kevinkitute;

/**
 *
 * @author Kevin
 */
import java.io.*; 
import java.sql.*; 

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.DefaultPieDataset;

public class PieChart_Gender {
   
   public static void main( String[ ] args )throws Exception {
      
      String mobilebrands[] = {
         "Female",   
         "Male",   
         "Other",            
      };
      
      /* Create MySQL Database Connection */
      Class.forName( "com.mysql.jdbc.Driver" );
      Connection connect = DriverManager.getConnection( 
         "jdbc:mysql://localhost:3306/kevinreception","root","");
      
      Statement statement = connect.createStatement( );
      ResultSet resultSet = statement.executeQuery("SELECT gender,COUNT(*) FROM users GROUP BY gender" );
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      while( resultSet.next( ) ) {
         dataset.setValue( 
         resultSet.getString( "Gender" ) ,
         Double.parseDouble( resultSet.getString( "COUNT(*)" )));
      }
      
      JFreeChart chart = ChartFactory.createPieChart(
         "Gender Pie Chart",   // chart title           
         dataset,          // data           
         true,             // include legend          
         true,           
         false );

      int width = 560;    /* Width of the image */
      int height = 370;   /* Height of the image */ 
      File pieChart = new File( "Pie_Chart.jpeg" );
      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
   }
}