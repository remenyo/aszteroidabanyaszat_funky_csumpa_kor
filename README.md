# Aszteroidabányászat

A BMEVIIIAB02 Szoftver projekt laboratórium funky_csumpa_kor csapat által fejlesztett Aszteroidabányászat játéka.

## Automatikus fordítás és indítás

A programot lefordítja és elindítja az alábbi szkript: `./_build_and_run.ps1`

## Sourceból fordítás

A projekt gyökerében futtatva:

`javac src/*.java && jar cfm app.jar ./MANIFEST.MF src/*.class && rm src/*.class`

vagy: `./_build.ps1`

## Futtatás

Az elkészült Jar file így indítható:

`java -jar app.jar`

vagy: `./_run.ps1`
