package com.twu;

import com.twu.Menu;

import java.lang.reflect.Array;

import java.util.*;

import com.twu.Event;

public class User {
    private String userName;
    private int userVote = 10;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserVote() {
        return userVote;
    }

    public void setUserVote(int userVote) {
        this.userVote = userVote;
    }

    public void QueryList(User user, List<Event> list) {
        List<Event> result = Arrays.asList(new Event[list.size()]);
        System.out.println("查看热搜排行榜");
        if (list.size() == 0) {
            System.out.println("暂无热搜");
        } else {

            int length = list.size();
            for (int i = 0; i < length; i++) {
                if (list.get(i).getPurchase().equals(true)) {
                    result.set(list.get(i).getPurchaseRange() - 1, list.get(i));
                }

            }

            List<Event> voteList = new ArrayList<>();
            for(int i = 0; i< list.size();i++){
                if(list.get(i).getPurchase().equals(false)){
                    voteList.add(list.get(i));
                }
            }

            for (int i = 0; i < voteList.size(); i++) {
                for (int m = 0; m + 1 < voteList.size() - i; m++) {
                    if (voteList.get(m).getVote() < voteList.get(m + 1).getVote()) {
                        Event temp;
                        temp = voteList.get(m + 1);
                        voteList.set(m + 1, voteList.get(m));
                        voteList.set(m, temp);
                    }
                }
            }

            int m = 0;
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) == null) {

                    result.set(i, voteList.get(m));
                    m++;
                }

            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + result.get(i).getEventName() + " " + result.get(i).getVote());
        }
        Menu.userMenu(user, result);
    }

    public void VoteEvent(User user, List<Event> list) {


        System.out.println("给热搜事件投票，您拥有" + userVote + "张票");

        System.out.println("请输入您要投票的事件名称");
        Scanner voteEvent = new Scanner(System.in);
        String event = voteEvent.next();
        if (list.size() == 0){
            System.out.println("没有相应的热搜事件");
        }


        System.out.println("请输入您要投的票数");
        Scanner voteNum = new Scanner(System.in);
        int num = Integer.parseInt(voteNum.next());
        if (num <= userVote) {
            for (int i = 0; i < list.size(); i++) {
                if (event.equals(list.get(i).getEventName())) {

                    if (list.get(i).getSuper().equals(false)) {
                        list.get(i).setVote(list.get(i).getVote() + num);
                    } else {
                        list.get(i).setVote(list.get(i).getVote() + num * 2);
                    }
                    System.out.println("投票成功");
                    this.userVote = this.userVote - num;
                    break;
                }
                if (i == list.size() - 1) {
                    System.out.println("没有找到您输入的热搜事件，请重试");
                    VoteEvent(user, list);
                }
            }
        } else {
            System.out.println("投票数量大于您所拥有的票数");
            Menu.userMenu(user, list);
        }

        Menu.userMenu(user, list);


    }

    public void PurchaseEvent(User user, List<Event> list) {

        List<Event> arrayList = new ArrayList<>(list);

        System.out.println("购买热搜");
        System.out.println("请输入要购买热搜的事件");
        Scanner name = new Scanner(System.in);
        String purchaseEvent = name.next();
        if (arrayList.size() == 0) {
            System.out.println("目前没有热搜，请先添加热搜！");
        }
        for (int i = 0; i < arrayList.size(); i++) {

            if (purchaseEvent.equals(arrayList.get(i).getEventName())) {
                System.out.println("请输入要购买热搜的排名");
                Scanner range = new Scanner(System.in);
                int purchaseRange = Integer.parseInt(range.next());
                System.out.println("请输入要购买热搜的金额");
                Scanner money = new Scanner(System.in);
                int purchaseMoney = Integer.parseInt(money.next());

                if (arrayList.get(purchaseRange - 1).getPurchase().equals(true)) {
                    if (purchaseMoney > arrayList.get(purchaseRange - 1).getPurchaseMoney()) {
                        arrayList.get(i).setPurchase(true);
                        arrayList.get(i).setPurchaseRange(purchaseRange);
                        arrayList.get(i).setPurchaseMoney(purchaseMoney);
                        arrayList.set(purchaseRange - 1, arrayList.get(i));
                        arrayList.remove(i);
                    } else {
                        System.out.println("金额不足");
                    }
                } else {


                    arrayList.get(i).setPurchaseRange(purchaseRange);
                    arrayList.get(i).setPurchase(true);
                    arrayList.get(i).setPurchaseMoney(purchaseMoney);

                }

                break;
            }
            if (i == arrayList.size() - 1) {
                System.out.println("没有找到您输入的热搜事件，请重试");
                Menu.userMenu(user, arrayList);
            }
        }

        Menu.userMenu(user, arrayList);

    }

    public void AddEvent(User user, List<Event> list) {
        System.out.println("添加热搜");
        System.out.println("请输入要添加热搜的名字");
        Scanner Name = new Scanner(System.in);
        Event event = new Event();
        event.setEventName(Name.next());
        List<Event> arrayList = new ArrayList<>(list);
        arrayList.add(event);
        System.out.println("添加热搜成功");
        Menu.userMenu(user, arrayList);
    }
}
