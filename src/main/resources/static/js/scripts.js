$(".btn-write-comment").click(writeComment);
$("#btn-delete-comment").click(removeAnswer);


function removeAnswer(e) {
	
	e.preventDefault();
	console.log('remove answer');
	
	var url = $("#delete-comment").attr("action");
	console.log("url " + url);
	$.ajax({
		type: 'post',
		url : url,
		success: function(result) {
			var btn = e.target;
			var comment = btn.closest('#comment-wrap');
			comment.remove();

		},
		error: function() {
			console.log('error');
		}		
	});
	
}

function writeComment(e) {
	
	e.preventDefault();
	console.log('add answer');
	
	var url = $(".write-comment").attr("action");
	console.log("url " + url);
	
	var queryString = $(".write-comment").serialize();
	console.log("queryString : " + queryString);
	
	$.ajax({
		type: 'post',
		url : url,
		data: queryString,
		success: function(result) {
			var template = $("#answerTemplate").html();
			var templateResult = template.format(result.writer.name, "2016-09-03" , result.contents, result.question.id, result.id);
			$(".qna-comment-slipp-articles").prepend(templateResult);			
			$("#txt-comment").val('');
		},
		error: function() {
			console.log('error');
		}		
	});
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};