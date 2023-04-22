
function informazioniSim() {

    // definisci il payload da inviare (in formato JSON)
    const payload = {
        "IdSim": document.getElementById("ICCID").value,
        "IdRecord": document.getElementById("IdRecord").value,
    };

    // esegui la richiesta POST
    $.ajax({
    type: "POST",
    url: "/SimService",
    contentType: "application/json",
    data: JSON.stringify(payload), // trasforma il payload in formato JSON
    success: function(data) {
        console.log("SUCCESS : ", data);
            /*if(data.retCode == 0){
                document.getElementById("formRichiesta").style.display="block";
                document.getElementById("payload").innerHTML=data;
            }else{
                document.getElementById("errore").style.display="block";
                document.getElementById("erroreMsg").innerHTML=data.retMsg;
            }*/
    },
    error: function(textStatus, errorThrown) {
        console.error("La richiesta POST ha generato un errore:");
        console.error(textStatus, errorThrown);
    }
    });

}


    /*
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://95.174.12.104:10674/SimService/Inquiry",
        data: JSON.stringify(informazioniSim),
        //dataType: "json",
        success: function (data) {
        console.log("SUCCESS : ", data);
            /*if(data.retCode == 0){
                if(data.Coverage == 0){
                    document.getElementById("formIniziale").style.display="none";
                    document.getElementById("coperto").style.display="block";
                }else{
                    document.getElementById("nonCoperto").style.display="block";
                }
            }else{
                document.getElementById("errore").style.display="block";
                document.getElementById("erroreMsg").innerHTML=data.retMsg;
            }//
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
}
*/
$( document ).ready(function() {
    console.log( "ready!" );

    $("#FormInformazioni").submit(function () {
        event.preventDefault();
      
        informazioniSim();
      });

});