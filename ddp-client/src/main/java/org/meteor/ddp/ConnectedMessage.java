/*
 * Copyright (C) 2014. Geoffrey Chandler.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.meteor.ddp;

/**
 * Message sent from the server indicating that the connection is successful and provides a session identifier.
 *
 * @author geoffc@gmail.com
 * @since 1/17/14 at 6:36 PM.
 */

public class ConnectedMessage {

    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}