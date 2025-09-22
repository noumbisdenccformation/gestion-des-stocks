import { Component, OnInit } from '@angular/core';
import {BouttonAction} from "../../../composants/boutton-action/boutton-action";
import {Pagination} from "../../../composants/pagination/pagination";
import {Router} from '@angular/router';
import {UtilisateurResponseDto} from '../../../../gs-api/src';
import {UtilisateurService} from '../../../services/utilisateur/utilisateur.service';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {ModalConfirmation} from '../../../composants/modal-confirmation/modal-confirmation';

@Component({
  selector: 'app-page-utilisateur',
  imports: [
    BouttonAction,
    Pagination,
    NgForOf,
    NgIf,
    NgClass,
    ModalConfirmation
  ],
  templateUrl: './page-utilisateur.html',
  styleUrl: './page-utilisateur.css'
})
export class PageUtilisateur implements OnInit {
  
  utilisateurs: UtilisateurResponseDto[] = [];
  isLoading = false;
  errorMessage = '';
  showDeleteModal = false;
  utilisateurToDelete: UtilisateurResponseDto | null = null;

  constructor(
    private readonly router: Router,
    private readonly utilisateurService: UtilisateurService
  ) {
  }

  ngOnInit(): void {
    this.loadUtilisateurs();
  }

  loadUtilisateurs(): void {
    this.isLoading = true;
    this.errorMessage = '';
    
    this.utilisateurService.getAllUtilisateurs().subscribe({
      next: (data: UtilisateurResponseDto[]) => {
        this.utilisateurs = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Erreur lors du chargement des utilisateurs: ' + (error.error?.message || error.message);
        this.isLoading = false;
      }
    });
  }

  nouvelUtilisateur(): void {
    this.router.navigate(['nouvelutilisateur']);
  }

  onUtilisateurDeleted(): void {
    this.loadUtilisateurs(); // Reload the list after deletion
  }

  modifierUtilisateur(id?: number): void {
    this.router.navigate(['nouvelutilisateur', id]);
  }

  supprimerUtilisateur(id?: number): void {
    if (id) {
      const utilisateur = this.utilisateurs.find(u => u.id === id);
      if (utilisateur) {
        this.utilisateurToDelete = utilisateur;
        this.showDeleteModal = true;
      }
    }
  }

  confirmerSuppression(): void {
    if (this.utilisateurToDelete?.id) {
      this.utilisateurService.deleteUtilisateur(this.utilisateurToDelete.id).subscribe({
        next: () => {
          this.loadUtilisateurs();
          this.fermerModal();
        },
        error: (error: any) => {
          this.errorMessage = 'Erreur lors de la suppression: ' + (error.error?.message || error.message);
          this.fermerModal();
        }
      });
    }
  }

  fermerModal(): void {
    this.showDeleteModal = false;
    this.utilisateurToDelete = null;
  }

  getDeleteMessage(): string {
    return `Êtes-vous sûr de vouloir supprimer l'utilisateur "${this.utilisateurToDelete?.nom || ''} ${this.utilisateurToDelete?.prenom || ''}" ?`;
  }

  voirDetails(utilisateur: UtilisateurResponseDto): void {
    console.log('Voir détails utilisateur:', utilisateur);
  }

  getUserGradient(index: number): string {
    const gradients = [
      'bg-gradient-to-br from-emerald-500/90 to-teal-600/90',
      'bg-gradient-to-br from-cyan-500/90 to-blue-600/90',
      'bg-gradient-to-br from-teal-500/90 to-green-600/90',
      'bg-gradient-to-br from-blue-500/90 to-indigo-600/90'
    ];
    return gradients[index % gradients.length];
  }

  getUserAvatarBg(index: number): string {
    const backgrounds = [
      'bg-gradient-to-r from-emerald-500 to-teal-600',
      'bg-gradient-to-r from-cyan-500 to-blue-600',
      'bg-gradient-to-r from-teal-500 to-green-600',
      'bg-gradient-to-r from-blue-500 to-indigo-600'
    ];
    return backgrounds[index % backgrounds.length];
  }

  getRoleBadgeStyle(index: number): string {
    const styles = [
      'bg-emerald-100 text-emerald-800 group-hover:bg-white/20 group-hover:text-white',
      'bg-blue-100 text-blue-800 group-hover:bg-white/20 group-hover:text-white',
      'bg-purple-100 text-purple-800 group-hover:bg-white/20 group-hover:text-white',
      'bg-orange-100 text-orange-800 group-hover:bg-white/20 group-hover:text-white'
    ];
    return styles[index % styles.length];
  }

  getRoleDotColor(index: number): string {
    const colors = [
      'bg-emerald-400 group-hover:bg-white',
      'bg-blue-400 group-hover:bg-white',
      'bg-purple-400 group-hover:bg-white',
      'bg-orange-400 group-hover:bg-white'
    ];
    return colors[index % colors.length];
  }

  getRandomLoginCount(): number {
    return Math.floor(Math.random() * 100) + 10;
  }

  getRandomActionCount(): number {
    return Math.floor(Math.random() * 500) + 50;
  }

  getRandomDaysActive(): number {
    return Math.floor(Math.random() * 365) + 30;
  }

  getRandomLastActivity(): string {
    const activities = ['Il y a 2 min', 'Il y a 1h', 'Il y a 3h', 'Hier', 'Il y a 2 jours'];
    return activities[Math.floor(Math.random() * activities.length)];
  }
}
