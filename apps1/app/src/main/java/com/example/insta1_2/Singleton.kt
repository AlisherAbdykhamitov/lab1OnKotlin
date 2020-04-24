package com.example.insta1_2

class Singleton() {


    companion object Factory {
        lateinit var newsList: ArrayList<News>
        @Volatile
        private var INSTANCE: Singleton? = null

        @Synchronized
        fun create():
                Singleton =
            INSTANCE ?: Singleton().also { INSTANCE = it }

        fun newsGenerator(): ArrayList<News> {
            val items = ArrayList<News>()
            val logotype = ArrayList<Int>()
            val author = ArrayList<String>()
            val image = ArrayList<Int>()
            val data = ArrayList<String>()
            logotype.add(R.drawable.loh);
            author.add("modny_garderob");
            image.add(R.drawable.photo1);
            data.add("Стильная футболка");

            logotype.add(R.drawable.log1);
            author.add("koreataldyk");
            image.add(R.drawable.photo2);
            data.add("Вы видели такую? ✔️Двойная сковорода. Удобно! Практично!\n" +
                    "Производство: Южная Корея");

            logotype.add(R.drawable.log2);
            author.add("afanasya");
            image.add(R.drawable.photo3);
            data.add("Топ 10 книг в моем личном рейтинге");


            logotype.add(R.drawable.log3);
            author.add("Boshnikov");
            image.add(R.drawable.photo4);
            data.add("Картинки на сайте: как сократить размер на 80% без потери качества" +
                    " В первую очередь все говорят, что изображения на сайте должны быть сочными, красивыми, полезными и " +
                    "т.д. Ок, с этим не спорю. Но хочу сделать акцент, что каждый элемент на вашем сайте " +
                    "должен помогать достигать вашей цели.");

            logotype.add(R.drawable.log4);
            author.add("veronika_tender");
            image.add(R.drawable.photo5);
            data.add("Мечта сбылась \uD83D\uDE48 Ну а в тот момент я думала над тем, что в жизни обязательно должны быть паузы. " +
                    "Такие паузы, когда с вами ничего не происходит, " +
                    "когда вы просто сидите и смотрите на Мир, а Мир смотрит на вас.");


            logotype.add(R.drawable.log5);
            author.add("anton_bekkuzhev_");
            image.add(R.drawable.photo6);
            data.add("TМеня искренне удивляют люди, которые спрашивают, нравится ли мне моя работа. Я не понимаю - а как может быть иначе? Для чего мучать себя и заниматься делом, которое не приносит удовольствие? " +
                    "Ради интереса заглянул в статистику и увидел, " +
                    "что около трети россиян ходят на работу с большой неохотой.");


            logotype.add(R.drawable.log6);
            author.add("sergeysuxov");
            image.add(R.drawable.photo7);
            data.add("Когда кажется, что радугу все таки получится попробовать на вкус, стоит лишь чуть повыше подпрыгнуть✨");

            logotype.add(R.drawable.log7);
            author.add("greenbookmovie");
            image.add(R.drawable.photo8);
            data.add("#GreenBookMovie is the winner of 3 Academy Awards including Best Picture. " +
                    "Own it NOW on Blu-ray, DVD and Digital [Link in Bio]");
            logotype.add(R.drawable.log8);
            author.add("state_of_survival");
            image.add(R.drawable.photo9);
            data.add("Survivor #Tip\n" +
                    "Heroes can bring you benefits even outside the battlefield.\n" +
                    "\n" +
                    "So far, there are four heroes who can add extra bonus to your gathering marches: " +
                    "Ghost for gas, Rusty for metal, Mike for food and Chef for wood!");

            logotype.add(R.drawable.log9);
            author.add("animals.co");
            image.add(R.drawable.photo10);
            data.add("Smile \uD83D\uDE0A | Photography by @aloe_the_dalmatian");

            logotype.add(R.drawable.log10);
            author.add("physics");
            image.add(R.drawable.photo11);
            data.add("Follow my personal @aram ❤");

            logotype.add(R.drawable.log11);
            author.add("box_khu");
            image.add(R.drawable.photo12);
            data.add("");

            logotype.add(R.drawable.log12);
            author.add("adamsandler");
            image.add(R.drawable.photo13);
            data.add("Loooved this man! Stopped what I was doing anytime Curly was on TV. " +
                    "Just like every other kid! RIP and Love to his family!");

            logotype.add(R.drawable.log13);
            author.add("abdykhamitov.a");
            image.add(R.drawable.photo14);
            data.add("Моя семья!");

            logotype.add(R.drawable.log14);
            author.add("_shakarbek");
            image.add(R.drawable.photo15);
            data.add("Я тебе люблю, будем всегда вместе;)");

            logotype.add(R.drawable.log15);
            author.add("ufc");
            image.add(R.drawable.photo16);
            data.add("What's on your playlist this week?");

            logotype.add(R.drawable.log17);
            author.add("dota2");
            image.add(R.drawable.photo18);
            data.add("Rehearsals are underway ahead of the premiere of True Sight, " +
                    "with jet lag making these two even crazier than usual" +
                    " \uD83D\uDE01 #truesight #ti9 #dota2 .");

            logotype.add(R.drawable.log16);
            author.add("csgo_dev");
            image.add(R.drawable.photo17);
            data.add("Today we are adding Half-Life: Alyx content to CS:GO. Pins, stickers, patches, and an exclusive " +
                    "Music Kit for Index owners are now available in-game! #CSGO");

            logotype.add(R.drawable.log18);
            author.add("kbtu_official");
            image.add(R.drawable.photo19);
            data.add("ПЕРВАЯ ПРЕДМЕТНАЯ ОЛИМПИАДА КБТУ СОСТОЯЛАСЬ⚡️");

            logotype.add(R.drawable.log19);
            author.add("trand_muz");
            image.add(R.drawable.photo20);
            data.add("Согласны или нет?\uD83D\uDC47\uD83C\uDFFB");
            for (i in logotype.indices) {
                val news = News(
                    logotype[i],
                    author[i],
                    image[i],
                    data[i],
                    i,
                    i
                )
                items.add(news)
            }
            newsList=items
            return items
        }

    }
}