package ru.job4j.question;

import java.util.*;

public class Analize {
    /**
     * Метод принимает два множества пользователей и производит их сравнение.
     * Создается временная мапа, в которую заносятся элементы начального списка
     * Изначально выполняется поиск в новом списке элементов с совпадающими ключами по id.
     * Если найдены элементы с совпадающим ключом, произвоодится их сравнение по именам
     * Если имена не совпадают, элемент изменен (changed++), иначе элемент добавлен (added++).
     * В последнюю очередь вычисляем удаленных.
     *
     * @param previous начальный список
     * @param current  конечный список с возможными изменениями
     * @return возвращает статистику выполненных изменений (количество добавленных, измененных и удаленных элементов)
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> tempMap = new HashMap<>();
        for (User userPrev : previous) {
            tempMap.put(userPrev.id, userPrev.name);
        }
        for (User userCurr : current) {
            if (tempMap.containsKey(userCurr.getId())) {
                if (!Objects.equals(userCurr.name, tempMap.get(userCurr.id))) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }
        info.deleted = previous.size() + info.added - current.size();
        return info;
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Info)) {
                return false;
            }
            Info info = (Info) o;
            return getAdded() == info.getAdded()
                    && getChanged() == info.getChanged()
                    && getDeleted() == info.getDeleted();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getAdded(), getChanged(), getDeleted());
        }
    }

    public static class User {

        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return getId() == user.getId()
                    && Objects.equals(getName(), user.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }
}
