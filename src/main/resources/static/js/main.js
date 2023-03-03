document.addEventListener('DOMContentLoaded', ready);
let body;

function ready() {
    body = document.querySelector('body');
    body.admin_tab = document.querySelector('#home-tab-pane table');
    body.user_tab = document.querySelector('#v-pills-profile table');
    body.add_form = document.querySelector('#profile-tab-pane form');
    body.admin_tab?.addEventListener('click', linkHandler);
    body.add_form?.addEventListener('submit', addUser);
    request().then(resolve => {
        drawTab(body.admin_tab, resolve);
    });
    request('/user', 'GET').then(user => {
        if (!user) return;
        let email, role;
        email = document.querySelector('.user_email');
        if (email) email.innerHTML = user.email;
        role = document.querySelector('.user_role');
        if (role) role.innerHTML = user.role;
        body.user_tab?.querySelector('tbody')?.insertAdjacentHTML('beforeend', getRow(user));
        document.querySelector('#user_info_page_singl tbody')?.insertAdjacentHTML('beforeend', getRow(user));
    });
}

function addUser(e) {
    e.preventDefault();
    request('/admin', 'POST', getJson(body.add_form.elements)).then(user => {
        body.admin_tab.querySelector('tbody').insertAdjacentHTML('beforeend', getRow(user, true));
        document.getElementById('home-tab').dispatchEvent(new Event("click"));
        body.add_form.reset();
    });
}

function linkHandler(e) {
    e.preventDefault();
    if (e.target.tagName == 'A' && e.target.classList.contains('eBtn')) {
        editUser(e.target.getAttribute('href'), document.querySelector('.myForm form'));
    }
    if (e.target.tagName == 'A' && e.target.classList.contains('btn-danger')) {
        deleteUser(e.target.getAttribute('href'));
        e.target.closest('tr').remove();
    }
}

function editUser(url, form) {
    request(url).then(user => {
        Array.from(form.elements).forEach(elem => {
            if (elem.name in user) {
                elem.value = user[elem.name];
            }
        });
    });
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        let user = getJson(form.elements);
        request(url, 'PUT', user).then(res => {
            request().then(resolve => {
                drawTab(body.admin_tab, resolve);
                form.querySelector('.btn-close').dispatchEvent(new Event("click"));
            });
        });
    });
}

function deleteUser(url) {
    request(url, 'DELETE').then((res) => {
    });
}

function getJson(elements) {
    let json = {};
    Array.from(elements).forEach(elem => {
        if (elem.hasAttribute('name') && elem.getAttribute('name') != '') {
            json[elem.name] = elem.value;
        }
    });
    return json;
}

function drawTab(tab, data) {
    let tbody = tab?.querySelector('tbody');
    if (!tbody) return;
    tbody.innerHTML = '';
    if (!data) return;
    if (Array.isArray(data)) {
        data.forEach(user => {
            tbody.insertAdjacentHTML('beforeend', getRow(user, true));
        });
    } else {
        tbody.insertAdjacentHTML('beforeend', getRow(data));
    }
}

function getRow(user, forAdmin = false) {
    if (!user) return;
    let buttons = '';
    if (forAdmin) {
        buttons = `<td><a href="/admin/${user.id}" class="btn btn-primary eBtn" data-bs-toggle="modal"
                   data-bs-target="#exampleModal">Edit</a></td><td><a href="/admin/${user.id}" class="btn btn-danger">Delete</a></td>`;
    }
    return `<tr><td class="align-middle">${user.id}</td><td class="align-middle">${user.firstName}</td><td class="align-middle">${user.lastName}</td><td class="align-middle">${user.age}</td><td class="align-middle">${user.email}</td><td class="align-middle">${user.role}</td>${buttons}</tr>`;
}

/*общий fetch-запрос*/
async function request(url = '/admin', method = false, body = false) {
    let response, options;
    if (method) options = {method};
    if (method && body) {
        options.headers = {'Content-Type': 'application/json;charset=utf-8'};
        options.body = JSON.stringify(body);
    }
    if (options) {
        response = await fetch(url, options);
    } else {
        response = await fetch(url);
    }
    try {
        return await response.json();
    } catch (e) {
        console.log('Не валидный json получен от сервера -', e);
    }
}