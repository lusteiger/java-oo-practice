package com.twu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.twu.Menu;

public class Administrator {

    private String adminName;
    private String adminPwd;

    public Administrator(String adminName, String adminPwd) {
        this.adminName = adminName;
        this.adminPwd = adminPwd;
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void QueryList(Administrator administrator, List<Event> list) {
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
        Menu.AdminMenu(administrator, result);
    }

    public void AddEvent(Administrator administrator, List<Event> list) {
        System.out.println("添加热搜");
        System.out.println("请输入要添加热搜的名字");
        Scanner Name = new Scanner(System.in);
        Event event = new Event();
        event.setEventName(Name.next());
        list.add(event);
        System.out.println("添加热搜成功");
        Menu.AdminMenu(administrator, list);
    }

    public void AddSuperEvent(Administrator administrator, List<Event> list) {

        List<Event> arrayList = new ArrayList<>(list);

        System.out.println("添加超级热搜");
        System.out.println("请输入要添加超级热搜的名字");
        Scanner Name = new Scanner(System.in);
        Event event = new Event();
        event.setEventName(Name.next());
        event.setSuper(true);
        arrayList.add(event);
        System.out.println("添加热搜成功");
        Menu.AdminMenu(administrator, arrayList);

    }
}
