package com.example.myprojectmolegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        tv=findViewById(R.id.textView2);
        tv.setText("\nהגיעה הזמן החפרפרות התחילו את הפלישה."+"\n  על מנת לעצור אותם תיצטרך ללחוץ על כל החפרפרות על המסך לפני שהמסך יתמלא והחפרפרות יפלשו לעולם," +"\nבהצלחה!"+"/nרגיל -לוח משחק 3x3 ככל שהשחקן שורד יותר זמן על ידי לחיצה על החפרפרות במסך (לוח משחק לא מלא חפרפרות)  אז החפרפרות מתחילות להופיע יותר מהר המשחק נגמר כאשר הלוח משחק מלא בחפרפרות.\n" +
                "\n" +
                "צינוק-סוג משחק זה פועל כמו normal רק שכל דקה מופיעה סוג חדש של חפרפרת שעושה דבר שונה כשלוחצים עליה למשל בונוס לשיא איבוד נקודות בשיא וכ\"ד המשחק נגמר כאשר הלוח משחק מלא בחפרפרות.\n" +
                "\n" +
                "החורים משתכפלים-סוג משחק זה פועל כמה normal רק שכל חצי דקה מתווספים חורים ללוח המשחק המשחק נגמר כאשר הלוח משחק מלא בחפרפרות.\n" +
                "\n" +
                "קשה-לוח משחק 10x10 ככל שהשחקן שורד יותר זמן על ידי לחיצה על החפרפרות במסך (לוח משחק לא מלא חפרפרות)  אז החפרפרות מתחילות להופיע יותר מהר המשחק נגמר כאשר הלוח משחק מלא בחפרפרות.\n");
    }
}