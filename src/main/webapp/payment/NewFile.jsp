<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>라디오 버튼 예제</title>
</head>
<body>
    <form>
        <input type="radio" name="textOption" value="option1" onchange="updateText(this.value)"> 옵션 1
        <input type="radio" name="textOption" value="option2" onchange="updateText(this.value)"> 옵션 2
        <input type="radio" name="textOption" value="option3" onchange="updateText(this.value)"> 옵션 3
    </form>
    
    <div id="displayText">여기에 텍스트가 나타납니다.</div>

    <script>
        function updateText(selectedValue) {
            var displayText = document.getElementById("displayText");

            if (selectedValue === "option1") {
                displayText.innerText = "옵션 1을 선택했습니다.";
            } else if (selectedValue === "option2") {
                displayText.innerText = "옵션 2를 선택했습니다.";
            } else if (selectedValue === "option3") {
                displayText.innerText = "옵션 3을 선택했습니다.";
            }
        }
    </script>
</body>
</html>