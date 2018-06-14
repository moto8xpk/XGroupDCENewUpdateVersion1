 var AnswerList=[];
 var rad = document.myForm.question1;
    var prev = null;
    for(var i = 0; i < rad.length; i++) {
        rad[i].onclick = function() {
            (prev)? prev.value:null;
            if(this !== prev) {
                prev = this;
            }
            console.log(this.value);
            if(this!==prev){
              AnswerList.add(this.value);
            }
        };
    }
