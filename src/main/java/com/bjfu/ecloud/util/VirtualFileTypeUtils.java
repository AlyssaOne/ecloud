package com.bjfu.ecloud.util;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;

public final class VirtualFileTypeUtils {
    public static String FILE_TYPE_OTHERS = "0";
    public static String FILE_TYPE_DOCUMENT = "1";
    public static String FILE_TYPE_PICTURE = "2";
    public static String FILE_TYPE_MOVIE = "3";
    public static String FILE_TYPE_MUSIC = "4";

    public static String getFileType(String fileName){
//        String postfix = fileName.substring(fileName.indexOf("."));
        if(isDocumentType(fileName)){
            return FILE_TYPE_DOCUMENT;
        }
        else if(isPictureType(fileName)){
            return FILE_TYPE_PICTURE;
        }
        else if(isMovieType(fileName)){
            return FILE_TYPE_MOVIE;
        }
        else if(isMusicType(fileName)){
            return FILE_TYPE_MUSIC;
        }
        return FILE_TYPE_OTHERS;
    }

    private static boolean isDocumentType(String fileName){
        ArrayList<String> documentTypeList = initDocumentTypeList();
        for(String suffix: documentTypeList){
            if(fileName.endsWith(suffix))
                return true;
        }
        return false;
    }

    private static ArrayList<String> initDocumentTypeList(){
        ArrayList<String> documentTypeList = new ArrayList<>();
        documentTypeList.add(".doc");
        documentTypeList.add(".docx");
        documentTypeList.add(".pdf");
        documentTypeList.add(".excl");
        documentTypeList.add(".ppt");
        documentTypeList.add(".pptx");
        documentTypeList.add(".txt");
        return documentTypeList;
    }

    private static boolean isPictureType(String fileName){
        ArrayList<String> pictureTypeList = initPictureTypeList();
        for(String suffix: pictureTypeList){
            if(fileName.endsWith(suffix))
                return true;
        }
        return false;
    }

    private static ArrayList<String> initPictureTypeList(){
        ArrayList<String> pictureTypeList = new ArrayList<>();
        pictureTypeList.add(".png");
        pictureTypeList.add(".jpg");
        pictureTypeList.add(".jpeg");
        return pictureTypeList;
    }

    private static boolean isMovieType(String fileName){
        ArrayList<String> movieTypeList = initMovieTypeList();
        for(String suffix: movieTypeList){
            if(fileName.endsWith(suffix))
                return true;
        }
        return false;
    }

    private static ArrayList<String> initMovieTypeList(){
        ArrayList<String> movieTypeList = new ArrayList<>();
        movieTypeList.add(".mp4");
        movieTypeList.add(".avi");
        movieTypeList.add(".mov");
        return movieTypeList;
    }

    private static boolean isMusicType(String fileName){
        ArrayList<String> musicTypeList = initMusicTypeList();
        for(String suffix: musicTypeList){
            if(fileName.endsWith(suffix))
                return true;
        }
        return false;
    }

    private static ArrayList<String> initMusicTypeList(){
        ArrayList<String> musicTypeList = new ArrayList<>();
        musicTypeList.add(".mp3");
        return musicTypeList;
    }
}
