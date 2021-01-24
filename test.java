/**
* プログラム名：タイピングゲーム
* 作成日： Tue Dec 29 02:21:17 JST 2020
*/
public class TypingChallenge extends Turtle {
    
    //起動処理
    public static void main(String[] args) {
        Turtle.startTurtle(new TypingChallenge(), args);
    }
    
    //タートルを動かす処理
    public void start() {
        window.size(1350,700);
        hide();
        // 入力ボックス
        InputTurtle input = new InputTurtle();
        input.toJapaneseMode();
        input.warp(500, 200);
        
        //出力ボックス
        ImageTurtle result = new ImageTurtle("notfound.png");
        result.size(400, 300);
        result.warpByTopLeft(30, 60);
        
        // 入力する文字を入れておく入れ物
        ListTurtle<CardTurtle> keys = new ListTurtle<CardTurtle>(true);
        keys.warpByTopLeft(30, 450);
        
        keys.addLast(new CardTurtle("といぷーどる"));
        keys.addLast(new CardTurtle("ちわわ"));
        keys.addLast(new CardTurtle("しばけん"));
        keys.addLast(new CardTurtle("ふれんちぶるどっぐ"));
        keys.addLast(new CardTurtle("みにちゅあだっくすふんど"));
        keys.addLast(new CardTurtle("ぽめらにあん"));
        keys.addLast(new CardTurtle("ぱぴよん"));
        keys.addLast(new CardTurtle("よーくしゃーてりあ"));
        keys.addLast(new CardTurtle("まるちーず"));
        keys.addLast(new CardTurtle("ぱぐ"));
        
        // 対応する写真を入れておく入れ物
        ListTurtle<ImageTurtle> values = new ListTurtle<ImageTurtle>(true);
        values.warpByTopLeft(30, 550);
        values.addLast(new ImageTurtle("01.png"));
        values.addLast(new ImageTurtle("02.png"));
        values.addLast(new ImageTurtle("03.png"));
        values.addLast(new ImageTurtle("04.png"));
        values.addLast(new ImageTurtle("05.png"));
        values.addLast(new ImageTurtle("06.png"));
        values.addLast(new ImageTurtle("07.png"));
        values.addLast(new ImageTurtle("08.png"));
        values.addLast(new ImageTurtle("09.png"));
        values.addLast(new ImageTurtle("10.png"));
        
        //スタートボタン
        ButtonTurtle Startbutton = new ButtonTurtle("スタート！");
        Startbutton.warpByTopLeft(600, 100);
        //決定ボタン
        ButtonTurtle OKbutton = new ButtonTurtle("OK！");
        OKbutton.warpByTopLeft(600, 250);
        
        //画像スタート
        while (true) {
            sleep(0.025);// 待つ        
            
            if(Startbutton.isClicked()){
                values.setCursor(0);
                // 画像だけ初期位置を設定しているけど文字の初期位置指定しなくて大丈夫？
                keys.setCursor(0);  
                result.looks(values.getObjectAtCursor());
                print("スタート");
            }
            else 
            {
            }
            
            if(OKbutton.isClicked()){
                print("ok");
                String key = input.text();

                /*
                    元の処理
                */
                if (keys.getObjectAtCursor().getText().equals(key)) {
                    // 見つかった
                    keys.getObjectAtCursor();
                    for(int i=0; i<=keys.getSize()-1; i++){
                        if(keys.get(i).equals(key)){
                           print("番地が見つかりました");
                        }
                    }

                    if (values.getObjectAtCursor().equals(key)) {　// 画像と文字列を比較しているからヒットしない
                        // 見つかった
                        print("values:見つかった");
                        values.moveCursorToNext();
                        input.clearText();
                    }
                }

                /*
                    一番簡単に実装した場合
                    一つずつ順番に進めていくだけならこれで十分

                    getObjectAtCursor(); 現在の参照先を取得
                    moveCursorToNext(); 一つ進める
                    今、何番目を参照しているかはこれで制御しているので、
                    for(int i=0; i<=keys.getSize()-1; i++)
                    でやっているような何番目にヒットしたか調べる処理は必要ない
                */
                if (keys.getObjectAtCursor().getText().equals(key)) {
                    keys.moveCursorToNext();
                    values.moveCursorToNext();
                    result.looks(values.getObjectAtCursor());
                    input.clearText();
                }

                /*
                    最初にやろうとしていた方法を動くように実装した場合
                */
                if (keys.getObjectAtCursor().getText().equals(key)) {
                    int currentIndex = -1;
                    for(int i=0; i<=keys.getSize()-1; i++){
                        if(keys.get(i).equals(key)){
                            // 該当する文字列の順番を保存してループから抜ける
                            currentIndex = i;
                            break;
                        }
                    }
                    if (currentIndex >= 0) {
                        keys.moveCursorToNext();
                        values.setCursor(currentIndex);
                        result.looks(values.getObjectAtCursor());
                        input.clearText();
                    }
                }
            }
            else{
            }
            update();// 再描画する
        }
    }
}