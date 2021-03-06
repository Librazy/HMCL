/*
 * Hello Minecraft! Launcher.
 * Copyright (C) 2017  huangyuhui <huanghongxun2008@126.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see {http://www.gnu.org/licenses/}.
 */
package org.jackhuang.hmcl.event;

import java.util.EventObject;
import java.util.HashMap;
import org.jackhuang.hmcl.task.Schedulers;

/**
 *
 * @author huangyuhui
 */
public final class EventBus {

    private final HashMap<Class<?>, EventManager<?>> events = new HashMap<>();

    public <T extends EventObject> EventManager<T> channel(Class<T> clazz) {
        if (!events.containsKey(clazz))
            events.put(clazz, new EventManager<>(Schedulers.computation()));
        return (EventManager<T>) events.get(clazz);
    }

    public void fireEvent(EventObject obj) {
        channel((Class<EventObject>) obj.getClass()).fireEvent(obj);
    }

    public static final EventBus EVENT_BUS = new EventBus();
}
