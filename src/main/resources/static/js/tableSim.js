$(document).ready(function () {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/getAllSim",
       
        success: function (valore) {
        console.log("SUCCESS : ", valore);
            $('#table').DataTable({
                data:valore,
                columns: [
                    { data: 'seriale' },
                    { data: 'tipoServizio' },
                    { data: 'areaCode' },
                    { data: 'data' },
                    { data: 'stato', render: function (data) { 
                        if(data==2) return '<i class="fa-solid fa-circle" style=" color: rgb(0, 255, 0);"></i>'
                        if(data == 20) return '<i class="fa-solid fa-circle" style=" color: #fbff00;"></i>' 
                        if(data == 3) return '<i class="fa-solid fa-circle" style=" color: rgb(255, 0, 0);"></i>'
                        }   
                    },
                    { data: 'operatore.nome' },
                    { data: 'dettagli' }
                ],
            });
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
});
