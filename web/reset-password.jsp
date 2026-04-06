<%-- 
    Document   : reset-password
    Created on : Apr 4, 2026, 10:57:53 PM
    Author     : ASUS
--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <style>
        @font-face {
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

        /* container chính ch?a form + neon */
        .container {
            position: relative;
            width: 400px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .forgot-box {
            width: 100%;
            padding: 40px 30px;
            background: #111827;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 238, 255, 0.3);
            position: relative;
            z-index: 1;
        }

        .forgot-box h2 {
            text-align: center;
            color: #0ef;
            font-size: 2em;
            margin-bottom: 30px;
        }

        .input-box {
            position: relative;
            margin: 20px 0;
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
            font-size: 1em;
            color: #fff;
            pointer-events: none;
            transition: 0.5s ease;
            letter-spacing: 1px;
        }

        .input-box input:focus ~ label,
        .input-box input:valid ~ label {
            top: 1px;
            font-size: 0.8em;
            background: #111827;
            padding: 0 6px;
            color: #0ef;
        }

        .btn {
            width: 100%;
            height: 45px;
            background: #0ef;
            border: none;
            outline: none;
            border-radius: 40px;
            cursor: pointer;
            font-size: 1em;
            color: #1f293a;
            font-weight: 600;
            letter-spacing: 1px;
            margin-top: 10px;
        }

        .btn:hover {
            background: #2c4766;
            color: #fff;
        }

        .back-link {
            margin-top: 15px;
            text-align: center;
        }

        .back-link a {
            color: #0ef;
            text-decoration: none;
            font-weight: 600;
        }

        .back-link a:hover {
            text-decoration: underline;
        }

        .error-message {
            color: #ff4d4d;
            font-size: 0.9em;
            margin-bottom: 10px;
            text-align: center;
        }

        @keyframes animateCircle {
            0% { background: #0ef; }
            25% { background: #2c4766; }
            50% { background: #0ef; }
            100% { background: #2c4766; }
        }
    </style>
</head>
<body>
    <div class="container">
        <form class="forgot-box" action="reset-password" method="post">
            <input type="hidden" name="identifier" value="${identifier}">

            <h2>Reset Password</h2>

            <div class="input-box">
                <input type="password" name="newPassword" required>
                <label>New Password</label>
            </div>

            <button type="submit" class="btn">Update Password</button>

            <div class="back-link">
                <a href="login">Back to Login</a>
            </div>

            <% String msg = (String)request.getAttribute("message"); %>
            <% if (msg != null) { %>
            <div class="error-message"><%= msg %></div>
            <% } %>
        </form>


    </div>
</body>
</html>