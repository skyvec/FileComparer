<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
    
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> My first JSP   </title>
                <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
                <script type="text/javascript">
                  $(document).ready(function()  {
                  $("div.desc1").hide();
                  $("input[name$='file1']").click(function() {
                                                            var test = $(this).val();
                                                            $("div.desc1").hide();
                                                            $("#" + test).show();
                                                            });
                                                });
                  $(document).ready(function()  {
                  $("div.desc2").hide();
                  $("input[name$='file2']").click(function() {
                                                            var test = $(this).val();
                                                            $("div.desc2").hide();
                                                            $("#" + test).show();
                                                            });
                                                });
                </script>

	</head>	
	<body>		
            <div class="jumbotron">
                <h1>Compare two files</h1>
            </div>
            
            

                <%--<form action="FileComparer" method="Run">			
			Please select file <br>
			<input type="text" name="color"size="20px">
			<input type="submit" value="Process">						
		</form>	--%>
                
                <form action="FileComparer" method="Post" enctype="multipart/form-data">                 

                    
                    <h2>Select file one</h2>
                    <div id="myRadioGroupFile1">
    
                        File Contains Multiple Columns <input type="radio" name="file1"  value="fileContains1"  />
    
                        File Does not Contain Multiple Columns<input type="radio" name="file1" value="fileDoesNotContain1" />
                        <br><br>
                        <div id="fileContains1"  class="desc1"> 
                          
                            File Contains Multiple Columns
                        
                            Separator:<br>
                            <input type="text" name="separator1"><br>
                            Column number:<br>
                            <input type="text" name="column1"><br>
                            Select file to upload:
                            <input type="file" name="fileContainsChooser1" id="fileContainsChooser1"/><br/><br/>
                           
                        </div>
                    
                        <div id="fileDoesNotContain1" class="desc1">
                            
                            Select file to upload:
                            <input type="file" name="fileDoesNotContainChoose1" id="fileDoesNotContainChoose1"/><br/><br/>
                            
                        </div>
                    </div>
                   
                    
                    <h2>Select file two</h2>    
                    <div id="myRadioGroupFile2">
    
                        File Contains Multiple Columns <input type="radio" name="file2"  value="fileContains2"  />
    
                        File Does not Contain Multiple Columns<input type="radio" name="file2" value="fileDoesNotContain2" />
                        <br><br>
                        <div id="fileContains2"  class="desc2"> 
                            
                            File Contains Multiple Columns
                        
                            Separator:<br>
                            <input type="text" name="separator2"><br>
                            Column number:<br>
                            <input type="text" name="column2"><br>
                            Select file to upload:
                            <input type="file" name="fileContainsChooser2" id="fileContainsChooser2"/><br/><br/>
                            
                        </div>
                    
                        <div id="fileDoesNotContain2" class="desc2">
                            
                            Select file to upload:
                            <input type="file" name="fileDoesNotContainChoose2" id="fileDoesNotContainChoose2"/><br/><br/>
                            
                        </div>
                    </div> 
                  
                   
                    <div class="row">    
                        <input type="submit" value="submit" />
                    </div>      
                </form>
                </div>     
	</body>	
</html>