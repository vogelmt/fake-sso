/*
 * Copyright © Région Nord Pas de Calais-Picardie, Département 91, Région Aquitaine-Limousin-Poitou-Charentes, 2016.
 *
 * This file is part of OPEN ENT NG. OPEN ENT NG is a versatile ENT Project based on the JVM and ENT Core Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation (version 3 of the License).
 *
 * For the sake of explanation, any module that communicate over native
 * Web protocols, such as HTTP, with OPEN ENT NG is outside the scope of this
 * license and could be license under its own terms. This is merely considered
 * normal use of OPEN ENT NG, and does not fall under the heading of "covered work".
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

package fr.wseduc.sso.filters;


import fr.wseduc.webutils.http.Binding;
import org.entcore.common.http.filter.ResourcesProvider;
import org.entcore.common.user.UserInfos;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

public class KeyringFilter implements ResourcesProvider {

	@Override
	public void authorize(HttpServerRequest resourceRequest, Binding binding, UserInfos user, Handler<Boolean> handler) {
		final String accessService = "/keyring/access/" + resourceRequest.params().get("serviceId");
		if (user.getApps() != null) {
			for (UserInfos.Application app : user.getApps()) {
				if (app.getAddress() != null && app.getAddress().contains(accessService)) {
					handler.handle(true);
					return;
				}
			}
		}
		handler.handle(false);
	}

}
