<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- <META HTTP-EQUIV="Content-Type" CONTENT="text/html; CHARSET=windows-1251"> -->



<title>Test JSP Page for International Characters</title>

<script type="text/javascript">
    function populate()
    {
        //document.getElementById("displayNameId").value=document.getElementById("nameId").value;
        document.getElementById("displayTextDataId").value=document.getElementById("textDataId").value;
    }
    
    function toUnicode(theString) 
    {
          var unicodeString = '';
          for (var i=0; i < theString.length; i++) {
            var theUnicode = theString.charCodeAt(i).toString(16).toUpperCase();
            while (theUnicode.length < 4) {
              theUnicode = '0' + theUnicode;
            }
            theUnicode = '\\u' + theUnicode;
            unicodeString += theUnicode;
          }
          return unicodeString;
    }
    
    function ajaxpopulate()
    {
        var textDatValue = document.getElementById("textDataId").value;
        var textDatValue1 = toUnicode(textDatValue);
        //alert(textDatValue1);
        //alert(unescape(textDatValue1));
        var timestamp = new Date();
        var serverUrl = "/characterencoding/test?textdata="+textDatValue1+"&timestamp="+timestamp.getTime();
        var ajaxReq = getXmlHttpRequest();
        var pingStatus = processSynchronousPing( ajaxReq , serverUrl );
        document.getElementById("displayTextDataId").value = pingStatus;
    }
    
    function processSynchronousPing(ajaxReq,serverUrl)
    {
        var pingStatus = false;
        ajaxReq.onreadystatechange=function()
        {
            if (ajaxReq.readyState==4 && ajaxReq.status==200)
            {
                var textData = ajaxReq.responseText;
                if( textData != "" )
                {
                    pingStatus = textData;
                }
                else
                {
                    pingStatus = false;
                }
            }
        }
        ajaxReq.open("GET",serverUrl,false);//For synchronous call
        ajaxReq.send(null);//For synchronous call
        return pingStatus;
    }
    
    function getXmlHttpRequest()
    {
        var xmlhttp;
        if (window.XMLHttpRequest) 
        {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else 
        {
            // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
    }
    
</script>


</head>
<body>

<form name="form1" method="get" action="?" enctype="application/x-www-form-urlencoded">
    <fieldset style="width: 600px;height:200;">
        <legend>Input &nbsp;</legend>
        <table>
            <tr> 
              <td>Text data:</td>
              <td>
                <textarea rows="4" cols="35" name="?" id="textDataId"></textarea>
              </td>
            </tr>
            <tr>
                <td/>
                <td>
                    <input type="button" name="jssubmit" value="Javascript Submit" onclick="populate();"/>
                    <input type="button" name="ajaxsubmit" value="Ajax Submit" onclick="ajaxpopulate();"/>
                </td>
            </tr>
        </table>
    </fieldset>
    
    <fieldset style="width: 600px;height:200;">
        <legend>Display &nbsp;</legend>
        <table>
            <tr> 
              <td>Text data:</td>
              <td>
                <textarea rows="4" cols="35" name="?" id="displayTextDataId"></textarea>
              </td>
            </tr>
        </table>
    </fieldset>
</form>


</body>
</html>