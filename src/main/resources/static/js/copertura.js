
function VerificaCopertura() {

    var formCopertura={
        "lat": $("#latitudine").val(),
        "lon": document.getElementById("longitudine").value,
        "appId": 100
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://95.174.12.104:10674/GisServerProxy/Coverage",
        data: JSON.stringify(formCopertura),
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer 08e20755-20e5-39c1-b9bc-d14835ac2f22');
        },
        success: function (data) {
        console.log("SUCCESS : ", data);
            if(data.retCode == 0){
                //if(data.Coverage == 0){
                    document.getElementById("formIniziale").style.display="none";
                    document.getElementById("coperto").style.display="block";
                    document.getElementById("pulsanteAttivazione").setAttribute("href","/gestioneSim/"+data.AreaCode);
                //}else{
                //    document.getElementById("nonCoperto").style.display="block";
                //}
            }else{
                document.getElementById("errore").style.display="block";
                document.getElementById("erroreMsg").innerHTML=data.retMsg;
            }
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
}

$( document ).ready(function() {
    console.log( "ready!" );

    $("#FormCopertura").submit(function () {
        event.preventDefault();
      
        VerificaCopertura();
      });

});