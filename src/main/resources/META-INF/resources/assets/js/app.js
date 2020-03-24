let host = location.origin+'/';//'https://controle-populacional.herokuapp.com/'
console.log(host);
$(document).ready(function(){

	$("#select-estado").change(function(){
		var img = this;
		var fade = 300;
		$('#table-estado').fadeOut(fade,function(){

			$('#img-estado').fadeOut(100,function(){
				$(this).attr('src','assets/img/'+img.value.toLowerCase()+'.png');
				$('#img-estado').fadeIn(100);
			})


			// $('#img-estado').attr('src','assets/img/'+img.value.toLowerCase()+'.png');
			$('#table-estado').html('');
			$.ajax({
			    url : host+"estado/"+img.value,
			    success: function(data){
			    	data.forEach(function(item, index){
			    		var atualizar = '<button type="button" class="btn btn-primary"'+
			    		              ' data-toggle="modal" data-target="#atualizar-Modal"'+
			    		              ' data-idCidade="'+item.cidade.id+'" ' + 
			    		              ' data-nomeCidade="'+item.cidade.nome+'"' +'">'+
                                      ' Atualizar '+
                                      '</button>';
			    		var excluir = '<button type="button" class="btn btn-danger"'+
			    		              ' data-toggle="modal" data-target="#excluir-Modal"'+
			    		              ' data-idCidade="'+item.cidade.id+'" ' + '">'+
                                      ' Excluir '+
                                      '</button>';
		    			$('#table-estado').append(
					          '<tr>'+
					            '<th scope="row">'+item.cidade.nome+'</th>'+
					            '<td>'+item.cidade.populacao+'</td>'+
					            '<td>US$ '+item.custoPopulacional.toFixed(2)+'</td>'+
					            '<td>R$ '+item.custoPopulacionalCotacao.toFixed(2)+'</td>'+
					            '<td>'+atualizar+' '+ excluir+'</td>'+
					          '</tr>'
		    				);
			    		// console.log(item);
			    		// console.log(index);
			    	});

				    $('#table-estado').fadeIn(fade);

			    },
			  	error: function(x, e){
			  		console.log(e);
			  		console.log(x);
			  	}
			});
		});

	});

	iniciar();


	function iniciar(){
		// console.log("Output;");
		// console.log(location.hostname);
		// console.log(document.domain);
		// alert(window.location.hostname)
		//
		// console.log("document.URL : "+document.URL);
		// console.log("document.location.href : "+document.location.href);
		// console.log("document.location.origin : "+document.location.origin);
		// console.log("document.location.hostname : "+document.location.hostname);
		// console.log("document.location.host : "+document.location.host);
		// console.log("document.location.pathname : "+document.location.pathname);

		$('#select-estado').find('option').remove().end();
		$.ajax({
		    url : host+"estado",
		    success: function(data){
		    	data.forEach(function(item, index){
		    		$('#select-estado').append('<option value="'+item.uf+'" '+(item.selecionado?'selected':'')+'>'+item.nome+'</option>');
		    		$('#recipient-uf').append('<option value="'+item.uf+'">'+item.nome+'</option>');
		    		// console.log(item);
		    		// console.log(index);
		    	});
		    	$("select").trigger('change');
		    },
		  	error: function(x, e){
		  		// console.log('teste1');
		  		// console.log(e);
		  		// console.log('teste2');
		  		// console.log(x);
		  	}
		});
	}

	$('#modal-excluir-submit').click(function(){
		var delId = $('#recipient-del-populacao-id').val();

		$.ajax({
		    url : host+"cidade/"+delId,
		    contentType: 'application/json',
		    type : 'DELETE',
		    success: function(data){
		    	// console.log('teste');
		    	// console.log(data);
		    	// data.forEach(function(item, index){
		    	// 	$('#select-estado').append('<option value="'+item.uf+'" '+(item.selecionado?'selected':'')+'>'+item.nome+'</option>');
		    	// 	$('#recipient-uf').append('<option value="'+item.uf+'">'+item.nome+'</option>');
		    	// 	// console.log(item);
		    	// 	// console.log(index);
		    	// });
		    	$("select").trigger('change');
		    	$('#excluir-Modal').modal('toggle');
		    },
		  	error: function(x, e, z){

		  		tratarErros(x, e, z,'excluir-Modal');
		  	}
		});

	})
	$('#modal-upd-submit').click(function(){
		var idCidade = $('#recipient-upd-populacao-id').val();
		var populacaoCidade = $('#recipient-upd-populacao').val();
		if(!!populacaoCidade){
			console.log('teste: '+idCidade)
		}else{
			alert('Deve preencher o campo População');
			return false;
		}

    	// console.log(data);
		$.ajax({
		    url : host+"cidade/"+idCidade+"/"+populacaoCidade,
		    contentType: 'application/json',
		    type : 'PATCH',
		    success: function(data){
		    	// console.log('teste');
		    	// console.log(data);
		    	// data.forEach(function(item, index){
		    	// 	$('#select-estado').append('<option value="'+item.uf+'" '+(item.selecionado?'selected':'')+'>'+item.nome+'</option>');
		    	// 	$('#recipient-uf').append('<option value="'+item.uf+'">'+item.nome+'</option>');
		    	// 	// console.log(item);
		    	// 	// console.log(index);
		    	// });
		    	$("select").trigger('change');
		    	$('#atualizar-Modal').modal('toggle');
		    },
		  	error: function(x, e, z){
		  		tratarErros(x, e, z,'atualizar-Modal');
		  	}
		});

	})

	$('#modal-submit').click(function(){
		var nomeCidade = $('#recipient-name').val();
		var ufCidade = $('#recipient-uf').val();
		var populacaoCidade = parseFloat($('#recipient-populacao').val());

        data =  {"populacao":populacaoCidade,
    			 "nome":nomeCidade,
    			 "estado":ufCidade};

    	// console.log(data);
		$.ajax({
		    url : host+"cidade",
		    data : JSON.stringify(data),
		    contentType: 'application/json',
		    type : 'POST',
		    success: function(data){
		    	// console.log('teste');
		    	// console.log(data);
		    	// data.forEach(function(item, index){
		    	// 	$('#select-estado').append('<option value="'+item.uf+'" '+(item.selecionado?'selected':'')+'>'+item.nome+'</option>');
		    	// 	$('#recipient-uf').append('<option value="'+item.uf+'">'+item.nome+'</option>');
		    	// 	// console.log(item);
		    	// 	// console.log(index);
		    	// });
		    	$("select").val(ufCidade);
		    	$("select").trigger('change');
		    	$('#exampleModal').modal('toggle');
		    },
		  	error: function(x, e, z){
		  		tratarErros(x, e, z,'exampleModal');
		  	}
		});
    });

	$('#atualizar-Modal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		// console.log(button);
		var recipient = button.data('idcidade') // Extract info from data-* attributes
		var nomeCidade = button.data('nomecidade') // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this)
		// console.log(nomeCidade);
		// console.log(recipient);
		modal.find('#upd-ModalLabel').html(nomeCidade);
		$('#recipient-upd-populacao-id').val(recipient);
	})

	$('#excluir-Modal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		// console.log(button);
		var recipient = button.data('idcidade') // Extract info from data-* attributes
		$('#recipient-del-populacao-id').val(recipient);
	})


function tratarErros(x, e, z,modal){
	var erro = '';
	console.log(x.responseJSON)
	if(!(typeof x.responseJSON === 'undefined')){
		// console.log("message");
		if(!(typeof x.responseJSON.messages === 'undefined')){
			// console.log("messages");
			x.responseJSON.messages.forEach(function(item, index){
				// console.log(item);
				erro += item + '<br>'
			});
		$('#error-message').html(erro);
		}else{
			erro = x.responseJSON.message;
			// console.log(erro);
		$('#error-message').html(erro);
		}
	}else{
		erro = x
	// 	console.log('teste erro');
			// console.log(e);
			// console.log(x);
			// console.log(z);
		$('#error-message').html(erro);
	}
// 	console.log('teste erro2');
	// console.log(e);
	// console.log(x);
	// console.log(z);
	$('#'+modal).modal('toggle');
	$('#error-modal').modal('toggle');
}

});