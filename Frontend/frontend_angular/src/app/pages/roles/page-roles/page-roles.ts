import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RolesService } from '../../../services/roles/roles.service';
import { RolesResponseDto } from '../../../../gs-api/src';
import { BouttonAction } from '../../../composants/boutton-action/boutton-action';
import { Pagination } from '../../../composants/pagination/pagination';
import { ModalConfirmation } from '../../../composants/modal-confirmation/modal-confirmation';

@Component({
  selector: 'app-page-roles',
  imports: [CommonModule, BouttonAction, Pagination, ModalConfirmation],
  templateUrl: './page-roles.html',
  styleUrl: './page-roles.css'
})
export class PageRoles implements OnInit {
  
  roles: RolesResponseDto[] = [];
  isLoading = false;
  errorMessage = '';
  showDeleteModal = false;
  roleToDelete: RolesResponseDto | null = null;

  constructor(
    private readonly router: Router,
    private readonly rolesService: RolesService
  ) {}

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    this.isLoading = true;
    this.errorMessage = '';
    
    this.rolesService.getAllRoles().subscribe({
      next: (data: RolesResponseDto[]) => {
        this.roles = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Erreur lors du chargement des rôles: ' + (error.error?.message || error.message);
        this.isLoading = false;
      }
    });
  }

  nouveauRole(): void {
    this.router.navigate(['nouveaurole']);
  }

  onRoleDeleted(): void {
    this.loadRoles();
  }

  deleteRole(id: number): void {
    this.rolesService.deleteRole(id).subscribe({
      next: () => {
        this.loadRoles();
        this.fermerModal();
      },
      error: (error) => {
        console.error('Erreur lors de la suppression:', error);
        this.errorMessage = 'Erreur lors de la suppression du rôle';
        this.fermerModal();
      }
    });
  }

  modifierRole(id?: number): void {
    this.router.navigate(['nouveaurole', id]);
  }

  supprimerRole(role: RolesResponseDto): void {
    this.roleToDelete = role;
    this.showDeleteModal = true;
  }

  confirmerSuppression(): void {
    if (this.roleToDelete && this.roleToDelete.id) {
      this.deleteRole(this.roleToDelete.id);
    }
  }

  fermerModal(): void {
    this.showDeleteModal = false;
    this.roleToDelete = null;
  }

  getDeleteMessage(): string {
    if (this.roleToDelete) {
      return `Êtes-vous sûr de vouloir supprimer le rôle "${this.roleToDelete.roleName}" ?`;
    }
    return 'Êtes-vous sûr de vouloir supprimer ce rôle ?';
  }

  voirDetails(role: RolesResponseDto): void {
    console.log('Voir détails rôle:', role);
  }

  getRoleGradient(index: number): string {
    const gradients = [
      'bg-gradient-to-br from-indigo-500/90 to-purple-600/90',
      'bg-gradient-to-br from-blue-500/90 to-indigo-600/90',
      'bg-gradient-to-br from-purple-500/90 to-pink-600/90',
      'bg-gradient-to-br from-cyan-500/90 to-blue-600/90'
    ];
    return gradients[index % gradients.length];
  }

  getRoleIconBg(index: number): string {
    const backgrounds = [
      'bg-gradient-to-r from-indigo-500 to-purple-600',
      'bg-gradient-to-r from-blue-500 to-indigo-600',
      'bg-gradient-to-r from-purple-500 to-pink-600',
      'bg-gradient-to-r from-cyan-500 to-blue-600'
    ];
    return backgrounds[index % backgrounds.length];
  }

  getRoleIcon(index: number): string {
    const icons = [
      'fas fa-crown',
      'fas fa-user-shield',
      'fas fa-user-cog',
      'fas fa-user-tie'
    ];
    return icons[index % icons.length];
  }

  getRoleBadgeBg(index: number): string {
    const badges = [
      'bg-indigo-500',
      'bg-blue-500',
      'bg-purple-500',
      'bg-cyan-500'
    ];
    return badges[index % badges.length];
  }

  getRandomPermissions(): string[] {
    const allPermissions = ['Lecture', 'Écriture', 'Suppression', 'Admin'];
    const count = Math.floor(Math.random() * 3) + 2;
    return allPermissions.slice(0, count);
  }

  getPermissionColor(index: number): string {
    const colors = ['bg-green-400', 'bg-blue-400', 'bg-red-400', 'bg-yellow-400'];
    return colors[index % colors.length];
  }

  getRandomUserCount(): number {
    return Math.floor(Math.random() * 50) + 5;
  }

  getRandomPermissionCount(): number {
    return Math.floor(Math.random() * 10) + 3;
  }

  getRandomDate(): string {
    const dates = ['12/01/2024', '15/02/2024', '08/03/2024', '22/04/2024'];
    return dates[Math.floor(Math.random() * dates.length)];
  }
}
