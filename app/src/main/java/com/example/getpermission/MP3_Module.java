package com.example.getpermission;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class MP3_Module {

    public Integer AnzahlMp3Files;
    public File[] fileArray;

    public String[] Titel=new String[500];
    public String[] Datei=new String[500];

    public int favoMax;

    public String[] Name=new String[500];

    public String[] FavoritName=new String[500];
    public String[] FavoritAnzeigeName=new String[500];


    public Integer Random ( Integer AktMP3)
    {
        boolean foundNewMp3=false;
        Integer NewMP3=0;

        Random rand = new Random();

        while (!foundNewMp3) {
            NewMP3 = rand.nextInt(AnzahlMp3Files);
            if (NewMP3 != AktMP3)
                foundNewMp3 = true;
            Log.d("aaa","Random "+String.valueOf (NewMP3));

        }

        return NewMP3;
    }


    public void MP3DateiLaden ()
    {
        File basePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

        File musicFile = new File(String.valueOf(basePath));
        fileArray = musicFile.listFiles();
        AnzahlMp3Files=fileArray.length;

        String[] help = new String[2];
        String[] help2 = new String[2];
        Integer y=0;

        for (Integer x=0; x<AnzahlMp3Files; x++)
        {
            //File file = new File(String.valueOf(fileArray[x]));
            help=(String.valueOf(fileArray[x]).split("Music/"));

            Arrays.sort(help);

            String MusikTitel = help[1];
            help=MusikTitel.split(".mp3");

            boolean mp3DateiVorhanden=containsString(String.valueOf (fileArray[x]));

            if (mp3DateiVorhanden)
            {
                Titel[y] = help[0];
                Datei[y] = String.valueOf (fileArray[x]);
                y=y+1;
            }
        }

        AnzahlMp3Files=y-1;
        if (AnzahlMp3Files<0) AnzahlMp3Files=0;

    }

    public void FavoritenMP3AnzeigeFuellen(){

        favoMax=0;
        initFavoritMP3Anzeige( );

        for (Integer i=0; i<500; i++)
        {
            if (FavoritName[i].equals (""))
            {

            }
            else
            {
                FavoritAnzeigeName[favoMax] =FavoritName[i]+"@"+ i;
                favoMax=favoMax+1;
            }
        }

        if (favoMax>0) {favoMax=favoMax-2;}

    }

    public  void initFavoritMP3Anzeige( ) {
        if (FavoritAnzeigeName[0]==null)
        {
         //Beimm aller ersten Projektaufruf müssen
         // die FavoritenAnzeigen initiert werden
            for (Integer i = 0; i < 500; i++) {
                FavoritAnzeigeName[i] = "";
            }
        }
    }

    public  void initMP3FavoritNamen( ) {

        if (FavoritName[0]==null) {
            //Beimm aller ersten Projektaufruf müssen
            // die FavoritenAnzeigen initiert werden
            for (Integer i = 0; i < 500; i++) {
                FavoritName[i] = "-";
            }
        }

    }

    public  void initMP3Variablen( ) {

        for (Integer i = 0; i < 500; i++) {
            Name[i] = "";
        }
    }


    private static boolean containsString( String s ) {
        return s.indexOf( "mp3" ) > -1 ? true : false;
    }
}
