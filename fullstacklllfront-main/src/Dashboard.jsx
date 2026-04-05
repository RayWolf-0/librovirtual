import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
    const navigate = useNavigate();
    
    // recuperacion de datos mediante localstorage
    const [userData, setUserData] = useState({
        nombre: localStorage.getItem('usuarioNombre') || "Usuario",
        rol: localStorage.getItem('usuarioRol') || "Docente"
    });

    // seguridad
    useEffect(() => {
        if (!localStorage.getItem('usuarioNombre')) {
            navigate('/');
        }
    }, [navigate]);

    // cerrar sesion
    const handleLogout = () => {
        localStorage.clear();
        navigate('/');
    };

    return (
        <div className="bg-surface font-body text-on-surface antialiased">
            <aside className="h-screen w-64 fixed left-0 top-0 bg-[#002366] dark:bg-[#0d141c] flex flex-col py-8 z-40 hidden md:flex">
                <div className="px-6 mb-10 flex items-center gap-4">
                    <div className="w-12 h-12 rounded-full bg-secondary-container flex items-center justify-center text-white font-bold border-2 border-secondary/30">
                        {userData.nombre.charAt(0)}
                    </div>
                    <div className="overflow-hidden">
                        <p className="text-white font-medium text-sm truncate">{userData.nombre}</p>
                        <p className="text-slate-300 text-xs opacity-80 truncate">
                            {userData.rol}
                        </p>
                    </div>
                </div>
                <nav className="flex-1 space-y-1">
                    <a className="flex items-center gap-3 text-white border-l-4 border-[#745b00] bg-white/10 px-6 py-4 transition-all translate-x-1" href="#">
                        <span className="material-symbols-outlined" data-icon="dashboard">dashboard</span>
                        <span className="font-inter font-medium text-sm tracking-wide">Dashboard</span>
                    </a>
                    <a className="flex items-center gap-3 text-slate-300 px-6 py-4 opacity-80 hover:bg-white/5 hover:opacity-100 transition-all" href="#">
                        <span className="material-symbols-outlined" data-icon="school">school</span>
                        <span className="font-inter font-medium text-sm tracking-wide">My Courses</span>
                    </a>
                    <a className="flex items-center gap-3 text-slate-300 px-6 py-4 opacity-80 hover:bg-white/5 hover:opacity-100 transition-all" href="#">
                        <span className="material-symbols-outlined" data-icon="fact_check">fact_check</span>
                        <span className="font-inter font-medium text-sm tracking-wide">Attendance</span>
                    </a>
                    <a className="flex items-center gap-3 text-slate-300 px-6 py-4 opacity-80 hover:bg-white/5 hover:opacity-100 transition-all" href="#">
                        <span className="material-symbols-outlined" data-icon="grade">grade</span>
                        <span className="font-inter font-medium text-sm tracking-wide">Grades</span>
                    </a>
                    <a className="flex items-center gap-3 text-slate-300 px-6 py-4 opacity-80 hover:bg-white/5 hover:opacity-100 transition-all" href="#">
                        <span className="material-symbols-outlined" data-icon="mail">mail</span>
                        <span className="font-inter font-medium text-sm tracking-wide">Communications</span>
                    </a>
                </nav>
                <div className="px-6 py-6">
                    <button className="w-full bg-secondary-container text-on-secondary-container font-semibold py-3 px-4 rounded-xl flex items-center justify-center gap-2 hover:brightness-95 transition-all active:scale-95">
                        <span className="material-symbols-outlined" data-icon="add">add</span>
                        <span>New Entry</span>
                    </button>
                </div>
                <div className="mt-auto border-t border-white/10 pt-4">
                    <a className="flex items-center gap-3 text-slate-300 px-6 py-3 opacity-80 hover:bg-white/5 hover:opacity-100 transition-all" href="#">
                        <span className="material-symbols-outlined" data-icon="help">help</span>
                        <span className="font-inter font-medium text-sm tracking-wide">Help Center</span>
                    </a>
                    <button 
                        onClick={handleLogout}
                        className="w-full flex items-center gap-3 text-slate-300 px-6 py-3 opacity-80 hover:bg-red-500/10 hover:text-red-400 transition-all"
                    >
                        <span className="material-symbols-outlined" data-icon="logout">logout</span>
                        <span className="font-inter font-medium text-sm tracking-wide">Logout</span>
                    </button>
                </div>
            </aside>

            <div className="md:pl-64 min-h-screen">
                {/* TopAppBar */}
                <header className="bg-white/80 backdrop-blur-md flex justify-between items-center w-full px-8 h-16 sticky top-0 z-50 shadow-sm shadow-slate-200/50">
                    <div className="flex items-center gap-4">
                        <span className="material-symbols-outlined text-primary md:hidden" data-icon="menu">menu</span>
                        <h1 className="text-xl font-bold tracking-tight text-[#00113a] font-manrope">
                            Colegio Bernardo O’Higgins
                        </h1>
                    </div>
                    <div className="flex items-center gap-6">
                        <div className="hidden lg:flex items-center bg-surface-container-low px-4 py-2 rounded-full gap-2 border border-outline-variant/20">
                            <span className="material-symbols-outlined text-outline text-sm" data-icon="search">search</span>
                            <input className="bg-transparent border-none focus:ring-0 text-sm w-48" placeholder="Buscar..." type="text" />
                        </div>
                        <div className="flex items-center gap-2">
                            <button className="p-2 text-slate-600 hover:bg-slate-100 transition-colors rounded-full relative">
                                <span className="material-symbols-outlined" data-icon="notifications">notifications</span>
                                <span className="absolute top-2 right-2 w-2 h-2 bg-secondary rounded-full"></span>
                            </button>
                            <div className="h-8 w-[1px] bg-outline-variant/30 mx-2"></div>
                            <div className="flex items-center gap-3 pl-2">
                                <span className="text-primary font-bold font-manrope text-sm hidden sm:inline">
                                    {userData.nombre}
                                </span>
                                <button onClick={handleLogout} className="material-symbols-outlined text-slate-400 hover:text-red-500 transition-colors">
                                    logout
                                </button>
                            </div>
                        </div>
                    </div>
                </header>
                <main className="p-8 space-y-8 max-w-7xl mx-auto">
                    <section className="space-y-1">
                        <h2 className="font-headline font-bold text-3xl text-primary tracking-tight">
                            Bienvenido de vuelta, {userData.nombre.split(' ')[0]}.
                        </h2>
                        <p className="text-on-surface-variant font-medium">
                            Panel de control para {userData.rol}. Sistema conectado a Oracle Cloud.
                        </p>
                    </section>

                    {/* Bento Grid Widgets (Manteniendo tu estructura) */}
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                        <div className="bg-surface-container-lowest p-6 rounded-xl shadow-sm border border-outline-variant/10 flex flex-col justify-between group hover:shadow-md transition-shadow">
                            <div className="flex justify-between items-start mb-4">
                                <div className="p-3 rounded-full bg-primary/5 text-primary">
                                    <span className="material-symbols-outlined" data-icon="schedule">schedule</span>
                                </div>
                                <span className="text-primary font-bold text-2xl">4</span>
                            </div>
                            <div>
                                <h3 className="font-headline font-bold text-lg text-primary">Cursos hoy</h3>
                                <p className="text-on-surface-variant text-sm mt-1">Próxima: 2° Medio B - 10:15 AM</p>
                            </div>
                        </div>
                        <div className="bg-surface-container-lowest p-6 rounded-xl shadow-sm border border-outline-variant/10 flex flex-col justify-between group hover:shadow-md transition-shadow">
                            <div className="flex justify-between items-start mb-4">
                                <div className="p-3 rounded-full bg-secondary/10 text-secondary">
                                    <span className="material-symbols-outlined" data-icon="unread_messages">notifications_unread</span>
                                </div>
                                <span className="text-secondary font-bold text-2xl">12</span>
                            </div>
                            <div>
                                <h3 className="font-headline font-bold text-lg text-primary">Mensajes no leídos</h3>
                                <p className="text-on-surface-variant text-sm mt-1">8 de Apoderados, 4 de Dirección</p>
                            </div>
                        </div>
                        <div className="bg-surface-container-lowest p-6 rounded-xl shadow-sm border border-outline-variant/10 flex flex-col justify-between group hover:shadow-md transition-shadow">
                            <div className="flex justify-between items-start mb-4">
                                <div className="p-3 rounded-full bg-tertiary-fixed text-on-tertiary-fixed">
                                    <span className="material-symbols-outlined" data-icon="assignment">assignment</span>
                                </div>
                                <span className="text-on-surface font-bold text-2xl">2</span>
                            </div>
                            <div>
                                <h3 className="font-headline font-bold text-lg text-primary">Próximas Evaluaciones</h3>
                                <p className="text-on-surface-variant text-sm mt-1">Ensayo Simce: Jueves 24</p>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
};

export default Dashboard;
