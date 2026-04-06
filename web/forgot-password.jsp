<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>

    <style>
        @font-face{
            font-family: 'Roboto';
            src: url('Roboto/Roboto-Regular.ttf');
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: #1f293a;
        }

        .forgot-box {
            width: 400px;
        }

        .forgot-box form {
            padding: 0 50px;
        }

        h2 {
            font-size: 2em;
            color: #0ef;
            text-align: center;
            margin-bottom: 20px;
        }

        .input-box {
            position: relative;
            margin: 25px 0;
        }

        .input-box input {
            width: 100%;
            height: 50px;
            background: transparent;
            border: 2px solid #2c4766;
            outline: none;
            border-radius: 40px;
            font-size: 1em;
            color: #fff;
            padding: 0 20px;
        }

        .input-box input:focus,
        .input-box input:valid {
            border-color: #0ef;
        }

        .input-box label {
            position: absolute;
            top: 50%;
            left: 20px;
            transform: translateY(-50%);
            color: #fff;
            pointer-events: none;
            transition: 0.5s;
        }

        .input-box input:focus ~ label,
        .input-box input:valid ~ label {
            top: 0;
            font-size: 0.8em;
            background: #1f293a;
            padding: 0 5px;
            color: #0ef;
        }

        .btn {
            width: 100%;
            height: 45px;
            background: #0ef;
            border: none;
            border-radius: 40px;
            cursor: pointer;
            font-size: 1em;
            font-weight: 600;
            color: #1f293a;
        }

        .btn:hover {
            background: #2c4766;
            color: #fff;
        }

        .back-link {
            text-align: center;
            margin-top: 15px;
        }

        .back-link a {
            color: #0ef;
            text-decoration: none;
        }

        .back-link a:hover {
            text-decoration: underline;
        }

    </style>
</head>

<body>

<div class="forgot-box">
    <form action="forgot-password" method="post">
        <h2>Forgot Password</h2>

        <div class="input-box">
            <input type="text" name="identifier" required>
            <label>Username or Email</label>
        </div>
        <% String msg = (String)request.getAttribute("message"); %>
        <% if (msg != null) { %>
        <div style="color: red; text-align:center; margin-bottom:10px;">
            <%= msg %>
        </div>
        <% } %>

        <button type="submit" class="btn">Reset Password</button>

        <div class="back-link">
            <a href="login">Back to Login</a>
        </div>
    </form>
</div>

</body>
</html>