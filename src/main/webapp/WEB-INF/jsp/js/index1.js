//var answerList=[]
var answerList={
		answers:[]
} 
var rad = document.myForm.question1;
    var prev = null;
    for(var i = 0; i < rad.length; i++) {
        rad[i].onclick = function() {
            (prev)? prev.value:null;
            var that=this;
            if(that !== prev) {
                prev = that;
                answerList.answers.push({
            	  	  "questionid" 		: that.getAttribute("class"),
            	  	  "answerques"		: that.value
//                answers["questionid"]=that.getAttribute("class");
//                answers["answerques"]=that.value;
                
                });
//                answerList.push(answers);
                console.log(that.value);
            }
            else
            	{
//	            	answers["questionid"]=that.getAttribute("class");
//	                answers["answerques"]=that.value;
//	                answerList.push(answers);
            	answerList.answers.push({
           	  	  "questionid" 		: that.getAttribute("class"),
           	  	  "answerques"		: that.value
//               answers["questionid"]=that.getAttribute("class");
//               answers["answerques"]=that.value;
               
               });
//               answerList.push(answers);
            		console.log(that.value);
            	}
            
            if(answerList.answers.length==20)
            	{
            		$.ajax({
            			type		: "POST",
            			contentType	: "application/json",
            			url			: "/DCE/Exam/getAnswerList",
            			data		: JSON.stringify(answerList.answers),
//            			timeout		: 10000000000,
            			success		: function(data) {
            				 var pTag = document.createElement("p");
            				 var t = document.createTextNode("You have "+ data +" /20 questions correct");
            				 pTag.appendChild(t);
            				 document.body.appendChild(pTag);
							console.log('post success');
						},
						error		: function() {
							console.log('post fail');
						},
						done		: function() {
							console.log('post done');
						},
            		
            		})
            	}
        };
    }
    
    
