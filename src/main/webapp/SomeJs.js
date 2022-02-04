 f1=0;
 f2=0;
 f3=0;
 f4=0;
 f5=0;
 f6=true;
f7=true;
reimReq=null;
function Display(formid) {
    if (f1%2==0) {
        document.getElementById(formid).style.display = "initial";
        
    } else {
        document.getElementById(formid).style.display = "none";
    }
  f1++;
  
  ;
}
function Display1(formid) {
    if (f2%2==0) {
        document.getElementById(formid).style.display = "initial";
        
    } else {
        document.getElementById(formid).style.display = "none";
    }
  f2++;
  
 
}
function Display2(formid) {
    if (f3%2==0) {
        document.getElementById(formid).style.display = "initial";
        
    } else {
        document.getElementById(formid).style.display = "none";
    }
  f3++;
  
  
}
function Display3(formid) {
    if (f4%2==0) {
        document.getElementById(formid).style.display = "initial";
        
    } else {
        document.getElementById(formid).style.display = "none";
    }
  f4++;
  
  
}
function getData(urls,methods,id) {

    let url = urls;

    
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = receiveData;

    xhr.open(methods, url, true) 
    xhr.send();

    function receiveData() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let r = xhr.responseText;
            

            rJson = JSON.parse(r);
          
            let insertSection=document.getElementById(id);
                insertSection.innerHTML=""
            
            
            let tableRow1=document.createElement('tr')
                 for( var i in rJson[0]){
                    let title = document.createElement('th')
                    let x=String(i).toUpperCase();
                   
                     title.innerHTML=x;
                    tableRow1.append(title)
                    insertSection.append(tableRow1);
                }
                
             
          

                for(let m in rJson){
                        let tableRowAbs=document.createElement('tr');
                        tableRowAbs.id=rJson[m].id;
                
                        let keys=Object.keys(rJson[0])
                        for(let k of keys){
                            
                                let tableDef = document.createElement('td');
                                let p=rJson[m][String(k)];
                            if(typeof p=='object'){

                                tableDef.innerHTML=p['fName']+" "+p['lName']+", "+p['availableReimbursement'];
                           
                                tableRowAbs.append(tableDef);
                                insertSection.append(tableRowAbs);

                             }else{
                                
                                tableDef.innerHTML=p;
                                tableRowAbs.append(tableDef);
                                 insertSection.append(tableRowAbs);
                                
                                }
                                
                                
                                }
                  
                                
                               

                }
                

                

            

            

           
        
        
    }
    
    
    


}
}
function getData3(urls,methods,id,formid) {

    let url = urls;

    
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = receiveData;

    xhr.open(methods, url, true) 
    xhr.send();

    function receiveData() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let r = xhr.responseText;
            

            rJson = JSON.parse(r);
          
            let insertSection=document.getElementById(id);
                insertSection.innerHTML=""
            
            
            let tableRow1=document.createElement('tr')
                 for( var i in rJson[0]){
                    let title = document.createElement('th')
                    let x=String(i).toUpperCase();
                    console.log(x);
                     title.innerHTML=x;
                    tableRow1.append(title)
                    insertSection.append(tableRow1);
                }
                
             
            theForm=document.getElementById(formid)   
            dropDownMenu=document.createElement('select') 
            dropDownMenu.innerHTML=""
            dropDownMenu.name='reimformid'
            dropDownMenu.title='Select a Form Id'
            dropDownMenu.id="dropDown"

                for(let m in rJson){
                        let tableRowAbs=document.createElement('tr');
                        tableRowAbs.id=rJson[m].id;
                        option=document.createElement('option') 
                        option.value=rJson[m].id;
                        option.innerHTML=rJson[m].id
                        dropDownMenu.append(option);
                        let keys=Object.keys(rJson[0])
                        for(let k of keys){
                            
                                let tableDef = document.createElement('td');
                                let p=rJson[m][String(k)];
                            if(typeof p=='object'){

                                tableDef.innerHTML=p['fName']+" "+p['lName']+", "+p['availableReimbursement'];
                           
                                tableRowAbs.append(tableDef);
                                insertSection.append(tableRowAbs);

                             }else{
                                
                                tableDef.innerHTML=p;
                                tableRowAbs.append(tableDef);
                                 insertSection.append(tableRowAbs);
                                
                                }
                                
                                
                                }
                  
                                theForm.append(dropDownMenu);
                               

                }
                

                

            

            

           
        
        
    }
    
    
    


}
}
function getData2(urls,methods) {

    let url = urls;

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = receiveData2;

    xhr.open(methods, url, true) 

    xhr.send();

    function receiveData2() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let r = xhr.responseText;
           

            rJson = JSON.parse(r);
            
            let insertSection=document.getElementById('insertData2');
            insertSection.innerHTML="";
            
            
            let tableRow1=document.createElement('tr')
                
            let title = document.createElement('th')
            let x="Messages"
            title.innerHTML=x;
            tableRow1.append(title)
            insertSection.append(tableRow1);
                
                
             
                
                    
                    for(let m in rJson){
                        let tableRowAbs=document.createElement('tr');
                      
                        
                            
                        let tableDef = document.createElement('td');
                       
                      
                       tableDef.innerHTML=rJson[m];
                       tableRowAbs.append(tableDef);
                       insertSection.append(tableRowAbs);
                        }
                  
            }
                    

                }
                

            

            

           f7=false; 
        }
        
    
        function getDataReim(idId,idValue,destination1) {
            let parsedId=document.getElementById(idId).value
            let valueChange=document.getElementById(idValue).value

            let url = `http://localhost:8086/ERS/${destination1}${parsedId}`;
        
            
            let xhr = new XMLHttpRequest();
           
            
            

            
            xhr.open('PUT', url, true) 
            xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
            
            xhr.send(`parseId=${parseId}&valueChange=${valueChange}`);
            
        }
            
 



