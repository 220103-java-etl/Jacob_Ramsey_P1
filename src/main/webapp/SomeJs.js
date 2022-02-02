 f1=0;
 f2=0;
 f3=0;
 f4=0;
 f5=0;
 f6=true;
f7=true;
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
function getData(urls,methods) {

    let url = urls;

    // this is where we would want to get the number input by the user 

    // 4 Steps to creating an AJAX call
    
    // 1. Create our XHR Object
    let xhr = new XMLHttpRequest();

    // 2. Set a callback function for the readystatechange event
    xhr.onreadystatechange = receiveData;

    // 3. Open the request
    xhr.open(methods, url, true) // true is default but I like to put it here

    // 4. Send the request
    xhr.send();

    function receiveData() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let r = xhr.responseText;
            console.log(r);

            rJson = JSON.parse(r);
            console.log(rJson);
            let insertSection=document.getElementById('insertData');
            if(f6){
            
            
            let tableRow1=document.createElement('tr')
                 for( var i in rJson[1]){
            let title = document.createElement('th')
            let x=String(i).toUpperCase();
            title.innerHTML=x;
            tableRow1.append(title)
            insertSection.append(tableRow1);
                }
                
             
                
                    
                    for(let m in rJson){
                        let tableRowAbs=document.createElement('tr');
                        console.log(rJson[m]);
                        let keys=Object.keys(rJson[0])
                        for(let k of keys){
                            
                        let tableDef = document.createElement('td');
                       let p=rJson[m][String(k)];
                       if(typeof p=='object'){

                           tableDef.innerHTML=p['fName']+" "+p['lName'];
                           tableRowAbs.append(tableDef);
                           insertSection.append(tableRowAbs);

                       }else{
                       console.log(p);
                       tableDef.innerHTML=p;
                       tableRowAbs.append(tableDef);
                       insertSection.append(tableRowAbs);
                        }}
                  
                    
                    

                }
                

            

            

           f6=false; 
        }
        
    }
    
    


}
}
function getData2(urls,methods) {

    let url = urls;

    // this is where we would want to get the number input by the user 

    // 4 Steps to creating an AJAX call
    
    // 1. Create our XHR Object
    let xhr = new XMLHttpRequest();

    // 2. Set a callback function for the readystatechange event
    xhr.onreadystatechange = receiveData2;

    // 3. Open the request
    xhr.open(methods, url, true) // true is default but I like to put it here

    // 4. Send the request
    xhr.send();

    function receiveData2() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let r = xhr.responseText;
            console.log(r);

            rJson = JSON.parse(r);
            console.log(rJson);
            let insertSection=document.getElementById('insertData2');
           
            
            
            let tableRow1=document.createElement('tr')
                
            let title = document.createElement('th')
            let x="Messages"
            title.innerHTML=x;
            tableRow1.append(title)
            insertSection.append(tableRow1);
                
                
             
                
                    
                    for(let m in rJson){
                        let tableRowAbs=document.createElement('tr');
                        console.log(m);
                        
                            
                        let tableDef = document.createElement('td');
                       
                      
                       tableDef.innerHTML=rJson[m];
                       tableRowAbs.append(tableDef);
                       insertSection.append(tableRowAbs);
                        }
                  
            }
                    

                }
                

            

            

           f7=false; 
        }
        
    
    
    




