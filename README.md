# Aszteroidab�ny�szat

A BMEVIIIAB02 Szoftver projekt laborat�rium funky_csumpa_kor csapat �ltal fejlesztett Aszteroidab�ny�szat j�t�ka.

## Automatikus ford�t�s �s ind�t�s

A programot leford�tja �s elind�tja az al�bbi szkript: `./_build_and_run.ps1`

## Sourceb�l ford�t�s

A projekt gy�ker�ben futtatva:

`javac src/*.java && jar cfm app.jar ./MANIFEST.MF src/*.class && rm src/*.class`

vagy: `./_build.ps1`

## Futtat�s

Az elk�sz�lt Jar file �gy ind�that�:

`java -jar app.jar`

vagy: `./_run.ps1`
