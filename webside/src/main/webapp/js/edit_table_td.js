    $(document).ready(function(){  
        var tds=$("td");  
        tds.click(tdclick);  
    });  
      
      
    function tdclick(){  
             var td=$(this);  
             //1,取出当前td中的文本内容保存起来  
            var text=td.text();  
            //2,清空td里面的内容  
            td.html(""); //也可以用td.empty();  
            //3，建立一个文本框，也就是input的元素节点  
            var input=$("<input>");  
            //4，设置文本框的值是保存起来的文本内容  
            input.attr("value",text);  
            input.keyup(function(event){  
                 var myEvent =event||window.event;  
                 var kcode=myEvent.keyCode;  
                if(kcode==13){  
                    var inputnode=$(this);  
                    var inputtext=inputnode.val();  
                    var tdNode=inputnode.parent();  
                    tdNode.html(inputtext);  
                    tdNode.click(tdclick);  
                }  
            });  
            //5，将文本框加入到td中  
            td.append(input);         
           //6,清除点击事件  
            td.unbind("click");  
    }
    
        //新建一个excel文件,设置为打印预览  
    function xlPrint(){  
      var xlApp;//存放Excel对象  
      var xlBook;//存放Excel工件簿文件  
      var xlSheet;//存放Excel活动工作表  
      try{  
        xlApp = new ActiveXObject("Excel.Application");  
      } catch(e){  
        alert("请启用ActiveX控件设置！");  
        return;  
      }  
       
         var xlBook = xlApp.Workbooks.Add;  
         var xlSheet = xlBook.Worksheets(1);  
      
         xlSheet.Cells(1,1).Value = "内容";       //这里为新建的excel增加数据          
       
      xlBook.Worksheets(1).Activate;  
      xlApp.Visible = true;  
      xlSheet.PrintPreview; // 打印预览  
      xlBook.Close(false); //如果为true,代表关闭的时候需要保存,false代表不需要关闭  
      xlApp.Quit();  
      xlApp = null;  
    }  