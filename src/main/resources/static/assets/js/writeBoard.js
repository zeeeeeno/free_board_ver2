/*
    게시글 작성
*/
$('#board_write').on('submit', function (event) {
    // Prevent the page from reloading
    event.preventDefault();

    let title = $("#board_title").val();
    let author = $("#board_author").val();
    let content = $("#board_contents").val();

    console.log('title: ' + title + ', author: ' + author + ', content: ' + content);

    if (title === '' || author === '' || content === '') {
        alert('빈칸이 존재합니다.');
        return;
    }

    let board = [];
    board.push({
        title: title,
        board: board,
        author: author
    })

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/board/insert",
        data: JSON.stringify(board),
        dataType: 'json',
        cache: false,
        timeout: 10000,
        success: function () {
            alert('게시글 등록');
            window.location.href("/board/list");
        },
        error: function (e) {
            alert("error: " + e);
            window.location.replace("/board/write");
        }
    })
});