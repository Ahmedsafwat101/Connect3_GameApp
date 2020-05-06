package com.e.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //check if there is game end or not
    boolean endGame=false;
    //false=yellow , true=red
   boolean playerActive=false;
   // indicate which one is the winner yellow=0, red=1
   int winnerNumber=-1;
   //gameCounter0--->9
    int gameCounter=0;

   // Colors  array will indicate each cell color in the board 0=yellow and 1=red
   int []Colors={-1,-1,-1,-1,-1,-1,-1,-1,-1};
   int [][]winningPositions={
                             {0,1,2},
                             {3,4,5},
                             {6,7,8},
                             {0,3,6},
                             {1,4,7},
                             {2,5,8},
                             {2,4,6},
                             {0,4,8}

   };

   public void Reset()  {
       ImageView reset=(ImageView)findViewById(R.id.imageView1);
       //reset.setTranslationY(-1000);
       reset.setImageResource(0);
       //reset.animate().translationY(1000);

       reset=(ImageView)findViewById(R.id.imageView2);
       //reset.setTranslationY(-1000);
       reset.setImageResource(0);
       //reset.animate().translationY(1000);

       reset=(ImageView)findViewById(R.id.imageView3);
       //reset.setTranslationY(-1000);
       reset.setImageResource(0);
       //reset.animate().translationY(1000);

       reset=(ImageView)findViewById(R.id.imageView4);
       //reset.setTranslationY(-1000);
       reset.setImageResource(0);
       //reset.animate().translationY(1000);

       reset=(ImageView)findViewById(R.id.imageView5);
       //reset.setTranslationY(-1000);
       reset.setImageResource(0);
       //reset.animate().translationY(1000);

       reset=(ImageView)findViewById(R.id.imageView6);
       //reset.setTranslationY(-1000);
       reset.setImageResource(0);
       //reset.animate().translationY(1000);

       reset=(ImageView)findViewById(R.id.imageView7);
       //reset.setTranslationY(-2000);
       reset.setImageResource(0);
       //reset.animate().translationY(2000);

       reset=(ImageView)findViewById(R.id.imageView8);
       //reset.setTranslationY(-2000);
       reset.setImageResource(0);
       //reset.animate().translationY(2000);

       reset=(ImageView)findViewById(R.id.imageView9);
       //reset.setTranslationY(-2000);
       reset.setImageResource(0);
       //reset.animate().translationY(2000);

       Colors= new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
       endGame=false;
       playerActive=false;
       winnerNumber=-1;
       gameCounter=0;
   }

   public boolean gameLogic()
   {
       if(Colors[0]==Colors[1] && Colors[0]==Colors[2] && Colors[0]!=-1)
       {
           winnerNumber=Colors[0];

           return endGame=true;
       }
       if(Colors[0]==Colors[3] && Colors[0]==Colors[6] && Colors[0]!=-1)
       {
           winnerNumber=Colors[0];
           return endGame=true;

       }
       if(Colors[3]==Colors[4] && Colors[3]==Colors[5] && Colors[3]!=-1)
       {
           winnerNumber=Colors[3];
           return endGame=true;

       }
       if(Colors[6]==Colors[7] && Colors[6]==Colors[8] && Colors[6]!=-1)
       {
           winnerNumber=Colors[6];
           return endGame=true;

       }
       if(Colors[1]==Colors[4] && Colors[1]==Colors[7] && Colors[1]!=-1)
       {
           winnerNumber=Colors[1];
           return endGame=true;

       }
       if(Colors[2]==Colors[5] && Colors[2]==Colors[8] && Colors[2]!=-1)
       {
           winnerNumber=Colors[2];
           return endGame=true;
       }
       if(Colors[0]==Colors[4] && Colors[0]==Colors[8] && Colors[0]!=-1)
       {
           winnerNumber=Colors[0];
           return endGame=true;
       }
       if(Colors[2]==Colors[4] && Colors[2]==Colors[6] && Colors[2]!=-1)
       {
           winnerNumber=Colors[2];
           return endGame=true;
       }
       if(gameCounter==8)
       {
           winnerNumber=-1;
           return endGame=true;

       }
       gameCounter++;

       return endGame;
   }

    public void GameOver()
    {

        Reset();
    }
   public void drop (View view) throws InterruptedException {
       ImageView counter=(ImageView)view;
       if (counter.getDrawable() != null )
       {
           Toast.makeText(getBaseContext(), "Choose another Cell ;)", Toast.LENGTH_SHORT).show();
       }
       else if(counter.getDrawable() == null && endGame!=true)  {
           int tagValue=Integer.parseInt(counter.getTag().toString());
           counter.setTranslationY(-1000);
           if (playerActive == true) {
               //moving from red to yellow
               counter.setImageResource(R.drawable.yellow);
               counter.animate().translationYBy(1000f).rotation(360).setDuration(600);
               playerActive = false;
               Colors[tagValue]=0;
           } else {
               //moving from yellow to red
               counter.setImageResource(R.drawable.red);
               counter.animate().translationYBy(1000f).rotation(360).setDuration(600);
               playerActive = true;
               Colors[tagValue]=1;
           }

           if(gameLogic())
           {

               LinearLayout linearlayout= (LinearLayout)findViewById(R.id.playAgainLayout);
               linearlayout.setVisibility(View.VISIBLE);

               TextView message= (TextView)findViewById(R.id.textView);
               if(winnerNumber==0)
               {
                   message.setText("Yellow has won!");
               }
               else if(winnerNumber==1)
               {
                   message.setText("Red has won!");

               }
               else
               {
                   message.setText("Draw!");
               }

           }
       }


   }
    public void playAgain(View view)
    {
        LinearLayout linearlayout= (LinearLayout)findViewById(R.id.playAgainLayout);
        linearlayout.setVisibility(View.INVISIBLE);

        Reset();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
