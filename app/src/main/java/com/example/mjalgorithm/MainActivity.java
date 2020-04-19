package com.example.mjalgorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private TextView tv_mjList ,tv_value1 ,tv_isWin;
    private ArrayList<Integer> arrayList ,temp1,temp2,temp3 ,temp20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_mjList = findViewById(R.id.tv_mjList);
        tv_value1=findViewById(R.id.tv_value1);
        tv_isWin=findViewById(R.id.tv_isWin);


        arrayList = new ArrayList<>();
        temp1 = new ArrayList<>();
        temp2 = new ArrayList<>();
        temp3 = new ArrayList<>();
        temp20 = new ArrayList<>();

//        int[] values = {11,12,12,13,13,13,14,14,15,15,15,16,16,17,17,17,18};
        int[] values = {11,11,};

        for(int value : values){
            arrayList.add(value);
        }
        tv_mjList.setText(arrayList.toString());


    }

    public void algorithm(View view) {

        //尋訪每個值拿來比對
        for(int i=0;i<arrayList.size()-1;i++){

            if(arrayList.get(i).equals(arrayList.get(i+1))){

                temp1.add(arrayList.get(i));
                temp1.add(arrayList.get(i+1));
                temp2 = (ArrayList<Integer>)arrayList.clone();
                temp2.remove(temp2.get(i+1));
                temp2.remove(temp2.get(i));

                //TODO 將temp2 >40的牌取出(即為大字)  如果沒有則不做 (全求單吊)
                if(temp2.size() != 0){
                    for(int j=temp2.size()-1;j>=0;j--){
                        if(temp2.get(j)>=40){
                            //這裡要從前面塞
                            temp3.add(0,temp2.get(j));
                            temp2.remove(j);
                        }
                    }
                }

                //TODO 目前  temp1 眼牌  temp3 大字 temp2 剩餘  要做temp3 相同的大字是否有三個 如果沒有就continue
                Log.v("leo","目前牌"+temp1.toString()+":"+temp2.toString()+":"+temp3.toString());


                if(temp3.size() ==0 ){        //沒大字
                    // TODO temp2刻順演算();
                    Log.v("leo","沒大字 :"+temp1.toString()+":"+temp2.toString());
                    if(isWinning(temp2)){
                        Log.v("leo","算完isWinning :"+isWinning(temp2));
                        tv_isWin.setText("胡牌!!\n");
                        tv_isWin.append(arrayList.toString());
                        break;
                    }else {
                        tv_isWin.setText("想詐胡阿!?\n");
                        tv_isWin.append(arrayList.toString());
                    }

                    // TODO temp3.size 是否為3n
                }else if (temp3.size()%3 ==0){
                    for(int k=0 ; k< (temp3.size()/3);k++){
                        if( Collections.frequency(temp3,temp3.get(k*3)) == 3  ){
                            // TODO  temp2刻順演算(); 大字為 三三一組
                            Log.v("leo","大字OK :"+temp1.toString()+":"+temp2.toString()+":"+temp3.toString());
                            if(isWinning(temp2)){
                                Log.v("leo","算完isWinning :"+isWinning(temp2));
                                tv_isWin.setText("胡牌!!\n");
                                tv_isWin.append(arrayList.toString());
                                break;
                            }else {
                                tv_isWin.setText("想詐胡阿!?\n");
                                tv_isWin.append(arrayList.toString());
                            }

                        }else{
                            // TODO 大字不齊
                            Log.v("leo","此牌無法胡"+temp1.toString()+":"+temp2.toString()+":"+temp3.toString());
                            tv_isWin.setText("想詐胡阿!?\n");
                            tv_isWin.append(arrayList.toString());
                        }
                    }
                }

                tv_value1.append(temp1.toString()+":"+temp2.toString()+":"+temp3.toString()+"\n");
                temp1.clear();
                temp3.clear();
            }
        }
        tv_value1.append(arrayList.toString());

    }

    private boolean isWinning (ArrayList temp2){
        boolean isWin = false ;
        if(temp2.size()==0) return isWin=true;
        temp20 = (ArrayList<Integer>)temp2.clone();
        Log.v("leo",temp20.toString());

        //需要幾組小組 (最多五組)
        int num = temp2.size()/3;
        //有 num 組 所以要做num次

        for(int i = 0; i<num;i++){
            //temp21 --->刻
            if(Collections.frequency(temp20,temp20.get(0)) >= 3 ){
//                temp21.add(0,temp20.get(0));
//                temp21.add(1,temp20.get(1));
//                temp21.add(2,temp20.get(2));
                temp20.remove(0);
                temp20.remove(0);
                temp20.remove(0);
                isWin=true;

            }else if(temp20.contains(temp20.get(0)+1)&&temp20.contains(temp20.get(0)+2)){
//                temp21.add(0,temp20.get(0));
//                temp21.add(1,temp20.get(temp20.indexOf(temp20.get(0)+1)));
                Log.v("leo",temp20.toString());
                int temp ;
                temp = temp20.get(0);
                temp20.remove(temp20.indexOf(temp));
                temp20.remove(temp20.indexOf(temp+1));
                temp20.remove(temp20.indexOf(temp+2));
                isWin=true;
            }else {
                isWin=false;
            }
        }

        return isWin;
    }
}
