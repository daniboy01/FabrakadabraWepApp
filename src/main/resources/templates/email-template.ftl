<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Email template</title>
</head>
<body>
<center class="wrapper">
    <table class="main" width="80%">

        <tr>
            <td height="8px" style="background-color: chartreuse"></td>
        </tr>

        <tr>
            <td>
                <p>FAbrakadabra játszóterek</p>
                <h2>Kedves, ${firstName}</h2>
                <p>Köszönjük a vásárlását a FAbrakadabra webáruházában, nagyon örülünk, hogy minket válaszott.
                    Rendelését rendszerünk rögzítette, munkatársunk hamarosan felveszi önnel a kapcsolatot, hogy
                    egyeztessen a telepítés és szállítás részleteiről.

                    Ha bármilyen kérdése lenne a rendeléssel kapcsolatban, a <p>info@fabrakadabra.hu</p> címen léphet velünk
                    kapcsolatba.
                </p>

                <div>
                    <h2>Rendelés #${orderID}</h2>
                    <h2>Ezt rendelted : ${itemName}</h2>
                </div>
            </td>
        </tr>
        <tr>
            <td height="4px" style="background-color: chartreuse"></td>
        </tr>
        <tr>
            <td>
                <h3>Rendelés rögzítve ekkor: ${createdAt}</h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3 style="float: left">Számlázási cím:</h3>
                <h3 style="float: right">Szállítási cím:</h3>
            </td>
        </tr>
        <tr>
            <td>
                <ul style="float:left;list-style: none">
                    <li>
                        <a>${Name}</a>
                    </li>
                    <li>
                        <a>${Address}</a>
                    </li>
                    <li>
                        <a>${PhoneNum}</a>
                    </li>
                </ul>
                <ul style="float:right;list-style: none" >
                    <li>
                        <a>${Name}</a>
                    </li>
                    <li>
                        <a>${Address}</a>
                    </li>
                    <li>
                        <a>${PhoneNum}</a>
                    </li>
                </ul>
            </td>
            <td>

            </td>
        </tr>

    </table>
</center>
</body>
</html>

